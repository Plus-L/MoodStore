package com.kci.moodstore.auth.bo;

import com.kci.moodstore.auth.api.bo.UserInfoInTokenBO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: moodstore
 * @description: 账号认证 - 检验BO （携带密码）
 * @author: PlusL
 * @create: 2022-11-10 22:00
 **/
@Getter
@Setter
@ToString
public class AuthAccountInVerifyBO extends UserInfoInTokenBO {

    /**
     * 密码
     */
    private String password;

    /**
     * 状态：1正常 | 0销毁
     */
    private Integer status;

}
