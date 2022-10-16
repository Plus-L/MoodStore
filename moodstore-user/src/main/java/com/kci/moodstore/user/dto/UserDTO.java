package com.kci.moodstore.user.dto;

/**
 * @program: moodstore-appointment
 * @description: User 传输实体
 * @author: PlusL
 * @create: 2022-10-15 21:53
 **/
public class UserDTO {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机
     */
    private Integer phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户类型：
     */
    private Integer type;

    /**
     * 用户状态
     */
    private Integer status;

    /**
     * 激活码
     */
    private String activationCode;

    /**
     * 头像
     */
    private String headerUrl;

}
