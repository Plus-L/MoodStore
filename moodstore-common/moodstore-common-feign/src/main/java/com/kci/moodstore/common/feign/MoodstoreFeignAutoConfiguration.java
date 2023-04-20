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

package com.kci.moodstore.common.feign;

import com.alibaba.cloud.sentinel.feign.SentinelFeignAutoConfiguration;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kci.moodstore.common.feign.sentinel.ext.MoodstoreSentinelFeign;
import com.kci.moodstore.common.feign.sentinel.handle.MoodstoreUrlBlockHandler;
import com.kci.moodstore.common.feign.sentinel.parser.MoodstoreHeaderRequestOriginParser;
import feign.Feign;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.concurrent.TimeUnit;

/**
 * sentinel 配置
 *
 * @author lengleng
 * @date 2020-02-12
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore(SentinelFeignAutoConfiguration.class)
public class MoodstoreFeignAutoConfiguration {

	@Bean
	@Scope("prototype")
	@ConditionalOnMissingBean
	@ConditionalOnProperty(name = "feign.sentinel.enabled")
	public Feign.Builder feignSentinelBuilder() {
		return MoodstoreSentinelFeign.builder();
	}

	@Bean
	@ConditionalOnMissingBean
	public BlockExceptionHandler blockExceptionHandler(ObjectMapper objectMapper) {
		return new MoodstoreUrlBlockHandler(objectMapper);
	}

	@Bean
	@ConditionalOnMissingBean
	public RequestOriginParser requestOriginParser() {
		return new MoodstoreHeaderRequestOriginParser();
	}

	/**
	 * OkHttp 客户端配置
	 * @return OkHttp 客户端配
	 */
	@Bean
	public OkHttpClient okHttpClient() {
		return new OkHttpClient.Builder().retryOnConnectionFailure(false) // 是否开启缓存
			.connectTimeout(30L, TimeUnit.SECONDS) // 连接超时时间
			.readTimeout(30L, TimeUnit.SECONDS) // 读取超时时间
			.writeTimeout(30L, TimeUnit.SECONDS)
			.followRedirects(true) // 是否允许重定向
			.build();
	}

}
