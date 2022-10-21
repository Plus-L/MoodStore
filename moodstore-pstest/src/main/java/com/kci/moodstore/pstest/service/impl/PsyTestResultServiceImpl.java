package com.kci.moodstore.pstest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kci.moodstore.framework.common.result.CommonResult;
import com.kci.moodstore.pstest.mapper.PsyTestResultMapper;
import com.kci.moodstore.pstest.model.PsyTestResult;
import com.kci.moodstore.pstest.service.PsyTestResultService;
import com.kci.moodstore.pstest.vo.PsyTestResultVO;
import org.springframework.stereotype.Service;

@Service
public class PsyTestResultServiceImpl extends ServiceImpl<PsyTestResultMapper, PsyTestResult> implements PsyTestResultService {

    @Override
    public CommonResult<PsyTestResultVO> getResultByScore(Long userId, Long testId, Integer score) {
        return null;
    }
}
