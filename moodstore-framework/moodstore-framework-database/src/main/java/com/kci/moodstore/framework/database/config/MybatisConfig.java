package com.kci.moodstore.framework.database.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @program: moodstore
 * @description: Mybatis 配置类
 * @author: PlusL
 * @create: 2022-10-16 09:21
 **/
@Configuration
@MapperScan({"com.kci.moodstore.**.mapper"})
public class MybatisConfig {

}
