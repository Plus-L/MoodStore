package com.kci.moodstore.pstest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kci.moodstore.framework.common.result.CommonResult;
import com.kci.moodstore.pstest.model.PsychometricTest;
import com.kci.moodstore.pstest.vo.PsyTestLikedVO;

import java.util.List;

public interface PsychometricTestService extends IService<PsychometricTest> {

    CommonResult<?> liked(Long psyTestId);

    CommonResult<List<PsyTestLikedVO>> getByLiked(Long userId);

}
