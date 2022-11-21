package com.kci.moodstore.auth.model;

import com.kci.moodstore.framework.common.model.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @program: moodstore
 * @description: 认证账号实体
 * @author: PlusL
 * @create: 2022-11-10 21:32
 **/
@Getter
@Setter
@ToString
public class AuthAccount extends BaseModel implements Serializable {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户类型：1 - 普通用户 | 0 - 管理员
     */
    private Integer type;

    /**
     * 用户状态：1 正常 | 0 无效
     */
    private Integer status;

    /**
     * 客户端ID
     */
    private String clientId;

    /**
     * 角色
     */
    private List<String> roles;


}
