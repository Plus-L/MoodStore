package com.kci.moodstore.pstest;

import com.alibaba.fastjson2.JSONObject;
import com.kci.moodstore.framework.cache.RedisService;
import com.kci.moodstore.pstest.service.PsyTestResultService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;

@SpringBootTest
@ContextConfiguration()
public class PsyResultTest {

    @Resource
    private PsyTestResultService psyTestResultService;

    @Resource
    private RedisService redisService;

    @Resource
    private StringRedisTemplate redisTemplate;

    @Test
    void getResultByItem() {
        System.out.println(psyTestResultService.getResultByItem(2L, 1582936040778297346L, "result2"));
    }

    @Test
    void redisUtil() {
        System.out.println(redisService);
    }

    @Test
    void strTest() {
        String question = redisTemplate.opsForValue().get("questionJson");
        JSONObject questionJson = JSONObject.parseObject(question);
    }

}
