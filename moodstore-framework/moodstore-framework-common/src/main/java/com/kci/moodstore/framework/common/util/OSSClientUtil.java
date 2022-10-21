package com.kci.moodstore.framework.common.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class OSSClientUtil implements InitializingBean {

    //阿里云OSS地址，这里看根据你的oss选择
    private final String endpoint = "oss-cn-shenzhen.aliyuncs.com";

    //阿里云OSS的accessKeyId
    private final String accessKeyId = "LTAI5t9i7ZRBDUBwt4oZtP1m";

    //阿里云OSS的密钥
    private final String accessKeySecret = "xxmeHvNnKtRIcV0QZD5zhaFZXiEDTr";

    //阿里云OSS上的存储块bucket名字
    private String bucketName = "moodstore";

    //阿里云图片文件存储目录
    // private String homeImageDir = "";

    // 定义公开静态变量
    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endpoint;
        ACCESS_KEY_ID = accessKeyId;
        ACCESS_KEY_SECRET = accessKeySecret;
        BUCKET_NAME = bucketName;
    }
}
