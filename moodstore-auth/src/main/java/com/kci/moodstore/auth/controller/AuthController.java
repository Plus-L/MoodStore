package com.kci.moodstore.auth.controller;

import com.alibaba.fastjson.JSON;
import com.kci.moodstore.auth.bo.TokenInfoBO;
import com.kci.moodstore.framework.common.constant.AuthConstant;
import com.kci.moodstore.framework.common.result.CommonResult;
import jdk.nashorn.internal.parser.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: moodstore
 * @description: 认证控制器，Oauth2获取令牌
 * @author: PlusL
 * @create: 2022-11-19 16:20
 **/
@Slf4j
@RestController
@RequestMapping("/oauth")
public class AuthController {

    @Autowired
    private TokenEndpoint tokenEndpoint;

    /**
     * Oauth2登录认证
     */
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public CommonResult<TokenInfoBO> postAccessToken(HttpServletRequest request,
                                                        @RequestParam String grant_type,
                                                        @RequestParam String client_id,
                                                        @RequestParam String client_secret,
                                                        @RequestParam(required = false) String refresh_token,
                                                        @RequestParam(required = false) String username,
                                                        @RequestParam(required = false) String password) throws HttpRequestMethodNotSupportedException {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("grant_type",grant_type);
        parameters.put("client_id",client_id);
        parameters.put("client_secret",client_secret);
        parameters.putIfAbsent("refresh_token",refresh_token);
        parameters.putIfAbsent("username",username);
        parameters.putIfAbsent("password",password);
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(request.getUserPrincipal(), parameters).getBody();
        TokenInfoBO oauth2TokenDto = TokenInfoBO.builder()
                .accessToken(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .tokenHead(AuthConstant.JWT_TOKEN_PREFIX).build();

        log.warn(JSON.toJSONString(oauth2TokenDto));

        return CommonResult.success(oauth2TokenDto);
    }
}
