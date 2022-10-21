package com.kci.moodstore.pstest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kci.moodstore.framework.common.result.CommonResult;
import com.kci.moodstore.pstest.model.PsyTestResult;
import com.kci.moodstore.pstest.vo.PsyTestResultVO;

public interface PsyTestResultService extends IService<PsyTestResult> {

    /**
     * 通过前端传来的分数返回一个测试结果并且将结果插入数据库和保存到redis中
     * @param userId 用户id
     * @param testId 测试id
     * @param score  分数
     * @return  PsyTestResultVO
     */
    CommonResult<PsyTestResultVO> getResultByScore(Long userId, Long testId, Integer score);

}
