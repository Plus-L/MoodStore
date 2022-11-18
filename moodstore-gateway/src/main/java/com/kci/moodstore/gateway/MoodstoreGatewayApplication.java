package com.kci.moodstore.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.kci.moodstore"})
public class MoodstoreGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoodstoreGatewayApplication.class, args);
    }

}
