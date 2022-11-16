package com.kci.moodstore.pstest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kci.moodstore.pstest.model.PULRelation;
import com.kci.moodstore.pstest.model.PsychometricTest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PULRelationMapper extends BaseMapper<PULRelation> {

    // 从数据库中根据我的喜欢获得心理测试列表
    List<PsychometricTest> getByLiked(@Param("userId") Long userId);

    // Redis 批量插入点赞数到数据库
    Integer updateBatchByTestId(@Param("likeCountMap") Map<Object, Object> likeCountMap);

    // Redis 批量插入测试数到数据库
    Integer updateTesterNum(@Param("testerCountMap") Map<Object, Object> testerCountMap);

}
