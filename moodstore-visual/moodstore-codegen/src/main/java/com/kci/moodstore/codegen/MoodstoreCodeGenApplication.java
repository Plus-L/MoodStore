/*
 * Copyright (c) 2020 moodstore4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kci.moodstore.codegen;

import com.kci.moodstore.common.datasource.annotation.EnableDynamicDataSource;
import com.kci.moodstore.common.feign.annotation.EnableMoodstoreFeignClients;
import com.kci.moodstore.common.security.annotation.EnableMoodstoreResourceServer;
import com.kci.moodstore.common.swagger.annotation.EnableMoodstoreDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lengleng
 * @date 2020/03/11 代码生成模块
 */
@EnableMoodstoreDoc
@EnableDynamicDataSource
@EnableMoodstoreFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableMoodstoreResourceServer
public class MoodstoreCodeGenApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoodstoreCodeGenApplication.class, args);
	}

}
