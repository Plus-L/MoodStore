package com.kci.moodstore.pstest;

import com.alibaba.fastjson2.JSONObject;
import com.kci.moodstore.pstest.mapper.PsychometricTestMapper;
import com.kci.moodstore.pstest.mapstruct.PsyTestMapStruct;
import com.kci.moodstore.pstest.model.PsychometricTest;
import com.kci.moodstore.pstest.service.LikeService;
import com.kci.moodstore.pstest.service.OssService;
import com.kci.moodstore.pstest.vo.PsyTestResultVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Set;

@SpringBootTest
class PstestApplicationTests {

    @Resource
    private OssService ossService;

    @Resource
    private LikeService likeService;

    @Resource
    private PsychometricTestMapper psyTestMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void getFileContent() {
        String result = ossService.getFileContent("mood-pstest/result/1582936040778297346-潜意识自测：你是怎样自欺欺人的？.txt");
        JSONObject jsonObject = JSONObject.parseObject(result);
        System.out.println(jsonObject.getString("rlt1"));
    }

    @Test
    void getByLiked() {
        PsychometricTest psychometricTest = psyTestMapper.selectById(1582936040778297346L);
        PsyTestResultVO resultVO = PsyTestMapStruct.INSTANCE.toResultVO(psychometricTest);
        System.out.println(resultVO);
    }

    @Test
    void redis() {
        System.out.println(Boolean.TRUE);
        System.out.println(Boolean.FALSE);
    }

    @Test
    void redisSetAndHash() {
        // 创建 Hash 集合存入每一个心理测试的点赞数
        // stringRedisTemplate.opsForHash().put("PSY_TEST_LIKED_COUNT", Long.toString(1582936040778297346L), Integer.toString(2));

        // 模拟点赞功能
        // Long likedCount = stringRedisTemplate.opsForHash().increment("PSY_TEST_LIKED_COUNT", Long.toString(1582936040778297346L), 1);
        // System.out.println(likedCount);

        // 创建点赞人数 Set 集合
        // stringRedisTemplate.opsForSet().add("PSY_TEST_LIKED_MEMBER:" + Long.toString(1582936040778297346L) + ":", Long.toString(2L));
        // stringRedisTemplate.opsForSet().add("PSY_TEST_LIKED_MEMBER:" + Long.toString(1582936040778297346L) + ":", Long.toString(89L));
        // stringRedisTemplate.opsForSet().add("PSY_TEST_LIKED_MEMBER:" + Long.toString(1582936040778297346L) + ":", Long.toString(13L));
        // stringRedisTemplate.opsForSet().remove("PSY_TEST_LIKED_MEMBER:" + Long.toString(1582936040778297346L) + ":", Long.toString(1L));
        Set<String> members = stringRedisTemplate.opsForSet().members("PSY_TEST_LIKED_MEMBER:1582936040778297346:");
        System.out.println(members);
    }

    @Test
    void quartzTest() {
        Set<String> keys = stringRedisTemplate.keys("PSY_TEST_LIKED_MEMBER:*");
        for (String str: Objects.requireNonNull(keys)) {
            String[] arr = str.split(":");
            System.out.println("心理测试编号：" + arr[1]);
            Set<String> members = stringRedisTemplate.opsForSet().members("PSY_TEST_LIKED_MEMBER:" + arr[1]);
            if ("New member".equalsIgnoreCase(members.iterator().next())) {
                continue;
            }
            System.out.println("点赞人编号：");
            for (String member: Objects.requireNonNull(members)) {
                System.out.println(member);
            }
            System.out.println("--------------------------");
        }
    }

}
