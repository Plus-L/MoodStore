package com.kci.moodstore.framework.common.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class OSSClientUtil implements InitializingBean {

    //阿里云OSS地址，这里看根据你的oss选择
    private final String endpoint = "oss-cn-shenzhen.aliyuncs.com";

    //阿里云OSS的accessKeyId
    private final String accessKeyId = "";    //  我把这keyid和密钥一上传到github，阿里云立马发短信给我，让我禁用。。。。有点牛逼

    //阿里云OSS的密钥
    private final String accessKeySecret = "";

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
