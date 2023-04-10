package com.kci.moodstore.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.kci.moodstore"})
@EnableDiscoveryClient
public class MoodstoreGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoodstoreGatewayApplication.class, args);
    }

}
