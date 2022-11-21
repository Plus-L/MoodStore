package com.kci.moodstore.auth.bo;

import com.kci.moodstore.auth.api.bo.UserInfoInTokenBO;
import lombok.*;
import org.springframework.stereotype.Service;

/**
 * @program: moodstore
 * @description: Token中携带的实体，存放于Redis中
 * @author: PlusL
 * @create: 2022-11-11 21:55
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class TokenInfoBO {

    /**
     * 通行Token
     */
    private String accessToken;

    /**
     * 刷新Token
     */
    private String refreshToken;

    /**
     * 访问令牌头前缀
     */
    private String tokenHead;

    /**
     * 过期时间 单位：秒
     */
    private Integer expiresIn;

}
