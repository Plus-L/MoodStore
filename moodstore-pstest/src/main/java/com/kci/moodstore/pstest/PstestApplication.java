package com.kci.moodstore.pstest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class PstestApplication {

    public static void main(String[] args) {
        SpringApplication.run(PstestApplication.class, args);
        log.info("------------- moodstore-pstest模块加载成功 ---------------");
    }

}
