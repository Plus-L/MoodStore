package com.kci.moodstore.user.model;

import com.kci.moodstore.framework.common.model.BaseModel;
import lombok.*;

import java.io.Serializable;


/**
 * @program: moodstore-appointment
 * @description: 用户实体模型
 * @author: PlusL
 * @create: 2022-10-15 17:10
 **/
@Getter
@Setter
@ToString
public class User extends BaseModel implements Serializable {

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
