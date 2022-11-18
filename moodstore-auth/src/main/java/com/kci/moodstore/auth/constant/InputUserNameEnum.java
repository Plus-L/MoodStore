package com.kci.moodstore.auth.constant;

/**
 * @program: moodstore
 * @description: 输入用户名类型  1 -- UserName  2 -- 手机号  3 -- 邮箱
 * @author: PlusL
 * @create: 2022-11-11 19:44
 **/
public enum InputUserNameEnum {

    /**
     * 用户名
     */
    USERNAME(1),

    /**
     * 手机号
     */
    PHONE(2),

    /**
     * 邮箱
     */
    EMAIL(3),;

    private final Integer value;

    public Integer value() {
        return value;
    }

    InputUserNameEnum(Integer value) {
        this.value = value;
    }

}
