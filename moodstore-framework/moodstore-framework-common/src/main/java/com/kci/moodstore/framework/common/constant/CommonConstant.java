package com.kci.moodstore.framework.common.constant;


import com.kci.moodstore.framework.common.util.OSSClientUtil;

/**
 * @program: seckill-framework
 * @description: 常用字段类
 * @author: PlusL
 * @create: 2022-07-25 14:27
 **/
public class CommonConstant {

    /**
     * token
     */
    public static final String COOKIE_NAME_TOKEN = "TOKEN";

    public static final Integer TOKEN_ADMIN_EXPIRE = 3600 * 24 * 2;

    public static final Integer TOKEN_NORMALUSER_EXPIRE = 3600 * 24;

}
