package com.kci.moodstore.pstest.service.impl;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kci.moodstore.framework.common.constant.CommonConstant;
import com.kci.moodstore.framework.common.result.CommonResult;
import com.kci.moodstore.framework.common.result.ResultStatus;
import com.kci.moodstore.pstest.mapper.PsychometricTestMapper;
import com.kci.moodstore.pstest.mapstruct.PsyTestMapStruct;
import com.kci.moodstore.pstest.model.PsychometricTest;
import com.kci.moodstore.pstest.service.OssService;
import com.kci.moodstore.pstest.service.PsychometricTestService;
import com.kci.moodstore.pstest.vo.PsychometricTestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.kci.moodstore.framework.common.constant.CommonConstant.*;
import static com.kci.moodstore.framework.common.constant.PsyTestConstant.*;

@Service
@Slf4j
public class PsychometricTestServiceImpl extends ServiceImpl<PsychometricTestMapper, PsychometricTest>
        implements PsychometricTestService {

    @Resource
    private OssService ossService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public CommonResult<Boolean> liked(Long userId, Long psyTestId) {
        // 从redis中获取psyDetail的基本信息
        String key = CommonConstant.CACHE_PSY_DETAILS_KEY + psyTestId;
        String psyDetailJson = stringRedisTemplate.opsForValue().get(key);
        if (StrUtil.isEmpty(psyDetailJson)) {
            // 操作逻辑不对，应该先发getPsyTestDetails请求才有可能发liked请求；不排除有人恶意访问
            return CommonResult.error(ResultStatus.ACCESS_LIMIT_REACHED);
        }
        // 有心理测试信息的缓存，延长过期时间
        stringRedisTemplate.expire(key, CACHE_PSY_TTL, TimeUnit.MINUTES);
        PsychometricTestVO psychometricTestVO = JSONUtil.toBean(psyDetailJson, PsychometricTestVO.class);

        // 1.判断当前登录用户是否已经点赞
        String setTestKey = PSY_TEST_LIKED_MEMBER + psyTestId;
        String setUserKey = PSY_TEST_LIKED_USER + userId;

        Boolean isMember = stringRedisTemplate.opsForSet().isMember(setTestKey, userId.toString());
        if (BooleanUtil.isFalse(isMember)) {
            // 2.如果未点赞，可以点赞
            // 2.1.redis中此心理测试点赞数+1（开启定时任务同步到 mysql 中）
            // TODO 我认为在管理员端功能的redis创建点赞数更好？
            Long likedCount = stringRedisTemplate.opsForHash().increment(PSY_TEST_LIKED_COUNT, psyTestId.toString(), 1);
            // 2.2.保存用户到 Redis 的 set 集合
            stringRedisTemplate.opsForSet().add(setTestKey, userId.toString());
            // 2.3.保存心理测试到 Redis 的 set 集合
            stringRedisTemplate.opsForSet().add(setUserKey, psyTestId.toString());
            // 2.4.在redis中修改isLike的值
            psychometricTestVO.setIsLike(Boolean.TRUE);
            psychometricTestVO.setLiked(Math.toIntExact(likedCount));

            return CommonResult.success(Boolean.TRUE);
        } else {
            // 3.如果已点赞，取消点赞
            // 3.1.redis点赞数-1（开启定时任务同步到 mysql 中）
            Long likedCount = stringRedisTemplate.opsForHash().increment(PSY_TEST_LIKED_COUNT, psyTestId.toString(), -1);
            // 3.2.把用户从 redis 的 set 集合移除
            stringRedisTemplate.opsForSet().remove(setTestKey, userId.toString());
            // 3.3.把心理测试从 redis 的 set 集合移除
            stringRedisTemplate.opsForSet().remove(setUserKey, psyTestId.toString());
            // 3.4.在 redis 中修改 isLike 的值
            psychometricTestVO.setIsLike(Boolean.FALSE);
            psychometricTestVO.setLiked(Math.toIntExact(likedCount));
            stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(psychometricTestVO), CACHE_PSY_TTL, TimeUnit.MINUTES);

            return CommonResult.success(Boolean.FALSE);
        }
    }

    @Override
    @Transactional
    public CommonResult<List<PsychometricTestVO>> getByLiked(Long userId) {
        // 1.从redis中获取用户点赞的文章列表ID
        Set<String> testList = stringRedisTemplate.opsForSet().members(PSY_TEST_LIKED_USER + userId);
        if (ObjectUtil.isEmpty(testList)) {
            return CommonResult.error(ResultStatus.FILE_NOT_EXIST);
        }
        // 2.先从redis查询文章信息
        // TODO 此处用到了 userId
        String key = null;
        List<PsychometricTestVO> testVOList = new ArrayList<>();

        assert testList != null;
        for (String psyTestId: testList) {
            key = CACHE_PSY_DETAILS_KEY + psyTestId;
            if (Boolean.TRUE.equals(stringRedisTemplate.hasKey(key))) {
                // redis 如果有心理测试的 key，则未过期，添加到 testVoList，并延长失效时间
                String psyDetailJson = stringRedisTemplate.opsForValue().get(key);
                PsychometricTestVO psychometricTestVO = JSONUtil.toBean(psyDetailJson, PsychometricTestVO.class);
                psychometricTestVO.setIsLike(Boolean.TRUE);

                testVOList.add(psychometricTestVO);
                stringRedisTemplate.expire(key, CACHE_PSY_TTL, TimeUnit.MINUTES);
            } else {
                // redis 没有心理测试的 key，则去数据库缓存重建至redis，并添加到 testVOList
                PsychometricTest psychometricTest = this.getById(psyTestId);
                PsychometricTestVO psychometricTestVO = PsyTestMapStruct.INSTANCE.toPsyTestVO(psychometricTest);
                psychometricTestVO.setIsLike(Boolean.TRUE);

                testVOList.add(psychometricTestVO);
                stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(psychometricTestVO), CACHE_PSY_TTL, TimeUnit.MINUTES);
            }
        }

        return CommonResult.success(testVOList);
    }

    @Override
    @Transactional
    public CommonResult<PsychometricTestVO> getPsyTestDetails(Long psyTestId) {
        // TODO userId先写死意思意思
        Long userId = 12L;
        String key = CACHE_PSY_DETAILS_KEY + psyTestId;
        String psyDetailJson = stringRedisTemplate.opsForValue().get(key);

        // 1. redis中有心理测试详情缓存，直接返回
        if (StrUtil.isNotBlank(psyDetailJson)) {
            return CommonResult.success(JSONUtil.toBean(psyDetailJson, PsychometricTestVO.class));
        }

        // 2. redis中没有心理测试，要么第一次访问，要么redis数据过期重建
        // 2.1. 从数据库中找数据重建缓存
        PsychometricTest psychometricTest = this.getById(psyTestId);
        if (ObjectUtil.isEmpty(psychometricTest)) {
            // 数据库中不存在数据则直接返回404不存在
            return CommonResult.error(ResultStatus.NOT_FOUND);
        }
        // 2.2. 数据库中存在数据，首先判断线程中的用户是否点过赞
        String setKey = PSY_TEST_LIKED_MEMBER + psyTestId;
        Boolean isMember = stringRedisTemplate.opsForSet().isMember(setKey, userId.toString());
        // 2.3. 因为redis中没有心理测试，此时我们顺便缓存重建一下
        PsychometricTestVO psychometricTestVO = PsyTestMapStruct.INSTANCE.toPsyTestVO(psychometricTest);
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(psychometricTestVO), CACHE_PSY_TTL, TimeUnit.MINUTES);

        if (BooleanUtil.isTrue(isMember)) {
            // 用户点过赞，isLike变成 true
            log.info(userId + "用户已经点过赞了！");
            psychometricTestVO.setIsLike(Boolean.TRUE);
        } else {
            log.info(userId + "点赞成功！");
            psychometricTestVO.setIsLike(Boolean.FALSE);
        }
        return CommonResult.success(psychometricTestVO);
    }

    @Override
    public CommonResult<?> getQuestionsByOSS(Long testId) {
        String key = "cache:questions:" + testId;
        // 1.先查缓存有没有
        String questionJson = stringRedisTemplate.opsForValue().get(key);
        // 2.有则直接返回
        if (StrUtil.isNotBlank(questionJson)) {
            return CommonResult.success(questionJson);
        }
        // 2.1.redis中也没有
        if (questionJson != null) {
            return CommonResult.error(ResultStatus.NOT_FOUND);
        }

        // 3.没有则重新构建缓存
        // 3.1.从oss中拿到文件内容（字符串内容）
        String filepath = OSS_QUESTION_FILE + testId + ".txt";  // TODO t暂定这样
        String question = ossService.getFileContent(filepath);
        // 3.2.OSS中无文件则放入空值解决缓存穿透问题
        if (StrUtil.isEmpty(question)) {
            stringRedisTemplate.opsForValue().set(key, "", CACHE_NULL_TTL, TimeUnit.MINUTES); // TODO 暂定这样
            return CommonResult.error(ResultStatus.NOT_FOUND);
        }

        // 3.3.转成json，存入redis
        JSONObject questionJson2 = JSONObject.parseObject(question);
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(questionJson2), CACHE_PSY_TTL, TimeUnit.DAYS);

        // 4.返回结果
        return CommonResult.success(questionJson2);
    }

    @Override
    public CommonResult<List<PsychometricTestVO>> getPsyTestByType(Integer type) {
        String key = "cache:psyTestList" + type;
        // 1.先查缓存有没有(看能不能返回一个List)
        String psyTestList = stringRedisTemplate.opsForValue().get(key);
        // 2.有则直接返回

        return null;
    }
}
