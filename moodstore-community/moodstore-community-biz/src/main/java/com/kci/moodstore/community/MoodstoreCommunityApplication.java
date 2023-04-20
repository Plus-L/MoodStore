package com.kci.moodstore.community;

import com.kci.moodstore.common.feign.annotation.EnableMoodstoreFeignClients;
import com.kci.moodstore.common.security.annotation.EnableMoodstoreResourceServer;
import com.kci.moodstore.common.swagger.annotation.EnableMoodstoreDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableMoodstoreDoc
@EnableMoodstoreFeignClients
@EnableMoodstoreResourceServer
@EnableDiscoveryClient
@SpringBootApplication
public class MoodstoreCommunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoodstoreCommunityApplication.class, args);
	}

}
