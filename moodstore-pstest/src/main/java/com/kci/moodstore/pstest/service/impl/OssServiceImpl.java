package com.kci.moodstore.pstest.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.kci.moodstore.framework.common.util.OSSClientUtil;
import com.kci.moodstore.pstest.service.OssService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public class OssServiceImpl implements OssService {

    @Override
    public String publishFileToOSS(MultipartFile file, String filename) {
        // 工具类取值
        String endPoint = OSSClientUtil.END_POINT;
        String accessKeyId = OSSClientUtil.ACCESS_KEY_ID;
        String accessKeySecret = OSSClientUtil.ACCESS_KEY_SECRET;
        String bucketName = OSSClientUtil.BUCKET_NAME;
        try {
            // 创建OSS实例
            OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
            // 上传文件流
            InputStream inputStream = file.getInputStream();
            filename = "mood-pstest/frontcover" + filename;
            // 调用oss方法实现上传
            ossClient.putObject(bucketName, filename, inputStream);
            // 关闭ossClient
            ossClient.shutdown();
            // 把上传之后的文件路径返回  需要符合阿里云oss的上传路径
            return "https://" + bucketName + "." + endPoint + "/" + filename;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
