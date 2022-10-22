package com.kci.moodstore.user.vo;

import com.kci.moodstore.framework.common.vo.BaseVO;

/**
 * @program: moodstore
 * @description: 用户详细信息展示类
 * @author: PlusL
 * @create: 2022-10-20 21:39
 **/
public class UserDetailVO extends BaseVO {

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
