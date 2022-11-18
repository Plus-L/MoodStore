package com.kci.moodstore.pstest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kci.moodstore.framework.common.result.CommonResult;
import com.kci.moodstore.pstest.model.PsychometricTest;
import com.kci.moodstore.pstest.vo.PsychometricTestVO;

import java.util.List;

public interface PsychometricTestService extends IService<PsychometricTest> {
    /**
     * 点赞
     * @param psyTestId 测试id
     * @return ？
     */
    CommonResult<Boolean> liked(Long userId, Long psyTestId);

    /**
     * 查看点赞收藏的列表
     * @param userId 用户id
     * @return 点赞文章列表
     */
    CommonResult<List<PsychometricTestVO>> getByLiked(Long userId);

    /**
     * 点击查看测试的详情页（未开始测试的状态）
     * @param testId 测试ID
     * @return 心理测试详情
     */
    CommonResult<PsychometricTestVO> getPsyTestDetails(Long testId);

    /**
     * 点击开始测试，就从阿里云OSS拿到txt文档转化成json返回，并存入redis
     * @param testId 测试ID
     * @return 题目
     */
    CommonResult<?> getQuestionsByOSS(Long testId);

    /**
     *  能根据 type 获得不同的各种测试List
     * @param type type
     * @return 测试VO集合
     */
    CommonResult<List<PsychometricTestVO>> getPsyTestByType(Integer type);

}
