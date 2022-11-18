package com.kci.moodstore.pstest;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.kci.moodstore"})
@Slf4j
@EnableDubbo
public class PstestApplication {

    public static void main(String[] args) {
        SpringApplication.run(PstestApplication.class, args);
        log.info("------------- moodstore-pstest模块加载成功 ---------------");
    }

}
