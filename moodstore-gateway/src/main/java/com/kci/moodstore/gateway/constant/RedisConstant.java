package com.kci.moodstore.gateway.constant;

import com.alibaba.fastjson2.JSON;

import java.net.URI;

/**
 * @program: moodstore
 * @description: 网关 缓存常量
 * @author: PlusL
 * @create: 2022-11-18 16:36
 **/
public class RedisConstant {

    public static final String RESOURCE_ROLES_MAP = "AUTH:RESOURCE_ROLES_MAP";

    private static final String PARTITION = ":";

    public static String getURIRedisKey(URI uri) {
        return RESOURCE_ROLES_MAP + PARTITION + JSON.toJSONString(uri);
    }

}
