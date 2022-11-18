package com.kci.moodstore.pstest.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import com.kci.moodstore.framework.common.constant.CommonConstant;
import com.kci.moodstore.framework.common.util.OSSClientUtil;
import com.kci.moodstore.pstest.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@Slf4j
public class OssServiceImpl implements OssService {

    public String endPoint = OSSClientUtil.END_POINT;
    public String accessKeyId = OSSClientUtil.ACCESS_KEY_ID;
    public String accessKeySecret = OSSClientUtil.ACCESS_KEY_SECRET;
    public String bucketName = OSSClientUtil.BUCKET_NAME;

    @Override
    public String getFileContent(String filePath) {
        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
        OSSObject ossObject = ossClient.getObject(bucketName, filePath);
        try {
            return this.readStream2Str(ossObject.getObjectContent());
        } catch (IOException e) {
            log.warn("方法 [getFileContent] 异常 异常信息：", e);
            return "从阿里云获取内容失败";
        }
    }

    public String readStream2Str(InputStream in) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = in.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            return byteArrayOutputStream.toString();
        } catch (IOException e) {
            log.warn("方法 [readStream2Str] 异常 异常信息：", e);
            return "文件流转换内容失败";
        } finally {
            in.close();
            byteArrayOutputStream.close();
        }
    }
}
