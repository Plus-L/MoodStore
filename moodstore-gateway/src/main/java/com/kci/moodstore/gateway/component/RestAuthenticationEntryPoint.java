package com.kci.moodstore.gateway.component;

import cn.hutool.json.JSONUtil;
import com.kci.moodstore.framework.common.result.CommonResult;
import com.kci.moodstore.framework.common.result.ResultStatus;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;

/**
 * @program: moodstore
 * @description: 自定义返回结果：没有登录或token过期时
 * ServerAuthenticationEntryPoint : 被ExceptionTranslationFilter用来作为认证方案的入口，
 * 即当用户请求处理过程中遇见认证异常时，被异常处理器（ExceptionTranslationFilter）用来开启特定的认证流程,
 * 在AuthenticationEntryPoint 接口中，只有一个commence()方法，用来开启认证方案。
 * 其中，request是遇到了认证异常的用户请求，response 是将要返回给用户的响应，authException 请求过程中遇见的认证异常。
 *
 * @author: PlusL
 * @create: 2022-11-18 18:06
 **/
public class RestAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {
    /**
     *  ServerAuthenticationEntryPoint : 被ExceptionTranslationFilter用来作为认证方案的入口，
     *  即当用户请求处理过程中遇见认证异常时，被异常处理器（ExceptionTranslationFilter）用来开启特定的认证流程,
     *  在AuthenticationEntryPoint 接口中，只有一个commence()方法，用来开启认证方案。
     *  其中，request是遇到了认证异常的用户请求，response 是将要返回给用户的响应，authException 请求过程中遇见的认证异常。
     */

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        String body= JSONUtil.toJsonStr(CommonResult.error(ResultStatus.UNAUTHORIZED));
        DataBuffer buffer =  response.bufferFactory().wrap(body.getBytes(Charset.forName("UTF-8")));
        return response.writeWith(Mono.just(buffer));
    }
}
