package com.kci.moodstore.auth.store;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.kci.moodstore.auth.api.bo.UserInfoInTokenBO;
import com.kci.moodstore.framework.cache.util.RedisUtil;
import com.kci.moodstore.framework.common.constant.CommonConstant;
import com.kci.moodstore.auth.bo.TokenInfoBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: moodstore
 * @description: Token 工厂
 * @author: PlusL
 * @create: 2022-11-11 21:33
 **/
@Component
// 配置变化时，RefreshScope的 Bean 会被刷新。
@Deprecated
@RefreshScope
public class TokenStore {

    private static final Logger logger = LoggerFactory.getLogger(TokenStore.class);

    @Autowired
    RedisUtil redisUtil;

    /**
     * 将用户的部分信息存储在token中，并返回token信息
     * UPDATE: 权限认证方案更新，采用SpringSecurity + jwt + oauth2。不再需要token存用户信息
     * @param userInfoInToken 用户在token中的信息
     * @return token信息
     */
    public TokenInfoBO storeAccessToken(UserInfoInTokenBO userInfoInToken) {
/*        TokenInfoBO tokenInfoBO = new TokenInfoBO();
        String accessToken = IdUtil.simpleUUID();
        String refreshToken = IdUtil.simpleUUID();

        tokenInfoBO.setUserInfoInToken(userInfoInToken);
        tokenInfoBO.setExpiresIn(CommonConstant.TOKEN_NORMALUSER_EXPIRE);


        // 一个用户会登陆很多次，每次登陆的token都会存在 uid_to_access里面
        // 但是每次保存都会更新这个key的时间，而key里面的token有可能会过期，过期就要移除掉
        List<String> existsAccessTokens = new ArrayList<>();
        // 新的token数据
        existsAccessTokens.add(accessToken + StrUtil.COLON + refreshToken);

        return tokenInfoBO;*/

        return null;

    }
}
