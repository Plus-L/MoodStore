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

    /**
     * 阿里云 OSS pstest模块 的答案文件路径
     */
    public static final String OSS_FILEPATH_PREFIX = "https://" + OSSClientUtil.BUCKET_NAME + "."
            + OSSClientUtil.END_POINT + "/" + "mood-pstest/result/";
}
