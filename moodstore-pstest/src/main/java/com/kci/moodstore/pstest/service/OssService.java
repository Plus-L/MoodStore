package com.kci.moodstore.pstest.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {

    /**
     * 上传文件到 阿里云 OSS，（图片也没问题）
     * @param file 文件
     * @return 返回成功与否
     */
    String publishFileToOSS(MultipartFile file, String filename);

}
