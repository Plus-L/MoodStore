package com.kci.moodstore.pstest.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kci.moodstore.framework.common.result.CommonResult;
import com.kci.moodstore.framework.common.result.ResultStatus;
import com.kci.moodstore.pstest.mapper.PsyTestResultMapper;
import com.kci.moodstore.pstest.mapstruct.PsyTestMapStruct;
import com.kci.moodstore.pstest.model.PsyTestResult;
import com.kci.moodstore.pstest.model.PsychometricTest;
import com.kci.moodstore.pstest.service.OssService;
import com.kci.moodstore.pstest.service.PsyTestResultService;
import com.kci.moodstore.pstest.service.PsychometricTestService;
import com.kci.moodstore.pstest.vo.PsyTestResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.concurrent.TimeUnit;

import static com.kci.moodstore.framework.common.constant.PsyTestConstant.*;

@Service
@Slf4j
public class PsyTestResultServiceImpl extends ServiceImpl<PsyTestResultMapper, PsyTestResult> implements PsyTestResultService {

    @Resource
    private OssService ossService;

    @Resource
    private PsychometricTestService psyTestService;

    @Resource
    private PsyTestResultMapper resultMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    @Transactional
    public CommonResult<PsyTestResultVO> getResultByItem(Long userId, Long testId, String Item) {
        // 1.生成url
        PsychometricTest psychometricTest = psyTestService.getById(testId);
        // 1.1.正常的前端传来的三个数据肯定准确，怕不法分子乱搞
        if (ObjectUtil.isEmpty(psychometricTest)) {
            return CommonResult.error(ResultStatus.PARAM_ERROR);
        }
        String filepath = OSS_RESULT_PATH + testId + "-" + psychometricTest.getTitle() + ".txt";

        // TODO 2.将txt转化成json并获取结果
        String result = ossService.getFileContent(filepath);
        // 2.1.同上
        if (StrUtil.isEmpty(result)) {
            return CommonResult.error(ResultStatus.NOT_FOUND);
        }
        JSONObject jsonResult = JSONObject.parseObject(result);
        result = jsonResult.getString(Item);

        // 3.封装成VO类
        PsyTestResultVO resultVO = PsyTestMapStruct.INSTANCE.toResultVO(psychometricTest);
        resultVO.setResult(result);

        // 4.将result实体插入数据库
        // 4.1.先判断之前是否测试过
        LambdaQueryWrapper<PsyTestResult> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(userId != null, PsyTestResult::getUserId, userId).eq(testId != null, PsyTestResult::getTestId, testId);
        PsyTestResult psyTestResult = getOne(queryWrapper);
        if (ObjectUtil.isNotNull(psyTestResult)) {
            // 4.2.测试过，则修改result，再update一下数据
            psyTestResult.setResult(result);
            this.updateById(psyTestResult);
        } else {
            // 4.3.未测试过，先redis+1，再定时任务更新数据库
            this.save(new PsyTestResult(userId, testId, result));
            stringRedisTemplate.opsForHash().increment(PSY_TEST_TESTER_COUNT, String.valueOf(testId), 1);
        }

        // 5.将resultVO类插入redis
        String resultKey = "cache:" + userId + "_result:" + testId;
        stringRedisTemplate.opsForValue().set(resultKey, JSONUtil.toJsonStr(resultVO));

        // 6.返回resultVO
        return CommonResult.success(resultVO);
    }

    @Override
    @Transactional
    public CommonResult<PsyTestResultVO> getResult(Long userId, Long testId) {
        String key = CACHE_PSY_DETAILS_KEY + ":" + userId + ":" + testId;
        // 1.从redis查询resultVO缓存
        String json = stringRedisTemplate.opsForValue().get(key);

        // 2.判断是否存在
        if (StrUtil.isNotBlank(json)) {
            // 2.1.存在直接返回
            return CommonResult.success(JSONUtil.toBean(json, PsyTestResultVO.class));
        }
        // TODO 2.2.redis中不存在，判断命中的是否是空值
        if (json != null) {
            // TODO json 不是 null
            // TODO 换句话说如果 json 不是 null 则 是”“，说明已经请求过一次并存入了”空字符串“缓存，返回一个错误信息。
            return CommonResult.error(ResultStatus.NOT_FOUND);
        }

        // 3.不存在，根据testId+useId查询数据库封装一个PsyTestResultVO
        PsyTestResultVO psyTestResultVO = resultMapper.getPsyTestResultVO(userId, testId);

        // 4.数据库中也不存在，返回错误
        if (ObjectUtil.isEmpty(psyTestResultVO)) {
            // TODO 4.1.将空值写入redis（解决缓存穿透问题）
            stringRedisTemplate.opsForValue().set(key, "", CACHE_NULL_TTL, TimeUnit.MINUTES);
            return CommonResult.error(ResultStatus.NOT_FOUND);
        }

        // 5.数据库中存在，说明只是redis正常过期，写入redis
        // TODO 理论上是token初始化一个时间，这里先搞2天意思意思
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(psyTestResultVO), 2L, TimeUnit.DAYS);
        return CommonResult.success(psyTestResultVO);
    }
}
