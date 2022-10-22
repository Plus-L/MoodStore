package com.kci.moodstore.framework.cache.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * @program: moodstore
 * @description: Cache配置类
 * @author: PlusL
 * @create: 2022-10-19 21:59
 **/
@Configuration
@EnableCaching
public class MoodStoreRedisPoolConfig {

    @Bean
    public JedisPool jedisPoolFactory() {
        return new JedisPool();
    }


}
