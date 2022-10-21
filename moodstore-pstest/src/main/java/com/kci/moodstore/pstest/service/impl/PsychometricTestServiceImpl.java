package com.kci.moodstore.pstest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kci.moodstore.framework.common.result.CommonResult;
import com.kci.moodstore.pstest.mapper.PsychometricTestMapper;
import com.kci.moodstore.pstest.mapper.UserTestLikedMapper;
import com.kci.moodstore.pstest.mapstruct.PsyTestMapStruct;
import com.kci.moodstore.pstest.model.PsychometricTest;
import com.kci.moodstore.pstest.service.PsychometricTestService;
import com.kci.moodstore.pstest.vo.PsyTestLikedVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PsychometricTestServiceImpl extends ServiceImpl<PsychometricTestMapper, PsychometricTest> implements PsychometricTestService {

    @Resource
    private UserTestLikedMapper likedMapper;

    @Override
    public CommonResult<?> liked(Long psyTestId) {
        return null;
    }

    @Override
    public CommonResult<List<PsyTestLikedVO>> getByLiked(Long userId) {
        List<PsychometricTest> testList = likedMapper.getByLiked(userId);

        List<PsyTestLikedVO> testLikedVOList = PsyTestMapStruct.INSTANCE.toLikedVOList(testList);
        return CommonResult.success(testLikedVOList);
    }
}
