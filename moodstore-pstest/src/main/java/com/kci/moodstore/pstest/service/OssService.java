package com.kci.moodstore.pstest.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {

    /**
     * 上传文件到 阿里云 OSS，（图片也没问题）
     * @param file 文件
     * @return 返回成功与否
     */
    //String publishFileToOSS(MultipartFile file, String filename);

    /**
     * 从阿里云OSS中获取txt文档中的内容
     * @param filePath 阿里云文件路径
     * @return 文件内容
     */
    String getFileContent(String filePath);
}
