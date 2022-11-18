package com.kci.moodstore.pstest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kci.moodstore.pstest.model.PsyTestResult;
import com.kci.moodstore.pstest.vo.PsyTestResultVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PsyTestResultMapper extends BaseMapper<PsyTestResult> {

    // 多表联查封装一个PsyTestResultVO类
    PsyTestResultVO getPsyTestResultVO(@Param("userId") Long userId, @Param("testId") Long testId);

}
