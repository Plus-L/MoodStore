package com.kci.moodstore.pstest.mapper;

import com.kci.moodstore.pstest.model.PsychometricTest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserTestLikedMapper {

    // 点赞插入数据

    // 从数据库中根据我的喜欢获得心理测试列表
    List<PsychometricTest> getByLiked(@Param("userId") Long userId);

}
