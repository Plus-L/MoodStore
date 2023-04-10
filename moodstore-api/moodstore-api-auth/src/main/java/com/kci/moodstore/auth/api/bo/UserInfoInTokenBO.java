package com.kci.moodstore.auth.api.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @program: moodstore
 * @description: Token中存放的用户信息
 * @author: PlusL
 * @create: 2022-11-10 21:03
 **/
@Getter
@Setter
@ToString
public class UserInfoInTokenBO {

    /**
     * 用户全局唯一ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户类型：1 - 普通用户 | 0 - 管理员
     */
//    private Integer type;

    /**
     * 权限列表
     */
    private List<String> roles;

}
