package com.kci.moodstore.auth.bo;

import com.kci.moodstore.auth.api.bo.UserInfoInTokenBO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

/**
 * @program: moodstore
 * @description: Token中携带的实体，存放于Redis中
 * @author: PlusL
 * @create: 2022-11-11 21:55
 **/
@Getter
@Setter
@ToString
public class TokenInfoBO {

    /**
     * 保存在token信息里面的用户信息
     */
    private UserInfoInTokenBO userInfoInToken;

    /**
     * 通行Token
     */
    private String accessToken;

    /**
     * 刷新Token
     */
    private String refreshToken;

    /**
     * 过期时间 单位：秒
     */
    private Integer expiresIn;

}
