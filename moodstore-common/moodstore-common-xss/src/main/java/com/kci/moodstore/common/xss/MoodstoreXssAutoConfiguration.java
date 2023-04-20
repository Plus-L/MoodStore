/*
 * Copyright (c) 2019-2029, Dreamlu 卢春梦 (596392912@qq.com & www.dreamlu.net).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kci.moodstore.common.xss;

import lombok.RequiredArgsConstructor;
import com.kci.moodstore.common.xss.core.DefaultXssCleaner;
import com.kci.moodstore.common.xss.core.FormXssClean;
import com.kci.moodstore.common.xss.core.JacksonXssClean;
import com.kci.moodstore.common.xss.core.XssCleaner;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * jackson xss 配置
 *
 * @author L.cm
 */
@AutoConfiguration
@RequiredArgsConstructor
@EnableConfigurationProperties(com.kci.moodstore.common.xss.config.MoodstoreXssProperties.class)
@ConditionalOnProperty(prefix = com.kci.moodstore.common.xss.config.MoodstoreXssProperties.PREFIX, name = "enabled",
		havingValue = "true", matchIfMissing = true)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class MoodstoreXssAutoConfiguration implements WebMvcConfigurer {

	private final com.kci.moodstore.common.xss.config.MoodstoreXssProperties xssProperties;

	@Bean
	@ConditionalOnMissingBean
	public XssCleaner xssCleaner(com.kci.moodstore.common.xss.config.MoodstoreXssProperties properties) {
		return new DefaultXssCleaner(properties);
	}

	@Bean
	public FormXssClean formXssClean(com.kci.moodstore.common.xss.config.MoodstoreXssProperties properties,
			XssCleaner xssCleaner) {
		return new FormXssClean(properties, xssCleaner);
	}

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer xssJacksonCustomizer(
			com.kci.moodstore.common.xss.config.MoodstoreXssProperties properties, XssCleaner xssCleaner) {
		return builder -> builder.deserializerByType(String.class, new JacksonXssClean(properties, xssCleaner));
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		List<String> patterns = xssProperties.getPathPatterns();
		if (patterns.isEmpty()) {
			patterns.add("/**");
		}
		com.kci.moodstore.common.xss.core.XssCleanInterceptor interceptor = new com.kci.moodstore.common.xss.core.XssCleanInterceptor(
				xssProperties);
		registry.addInterceptor(interceptor)
			.addPathPatterns(patterns)
			.excludePathPatterns(xssProperties.getPathExcludePatterns())
			.order(Ordered.LOWEST_PRECEDENCE);
	}

}
