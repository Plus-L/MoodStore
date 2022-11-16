package com.kci.moodstore.pstest.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kci.moodstore.pstest.mapper.PULRelationMapper;
import com.kci.moodstore.pstest.model.PULRelation;
import com.kci.moodstore.pstest.service.LikeService;
import com.kci.moodstore.pstest.service.PULRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.kci.moodstore.framework.common.constant.PsyTestConstant.PSY_TEST_LIKED_USER;

@Service
@Slf4j
public class LikeServiceImpl implements LikeService {

    @Resource
    private PULRelationMapper likedMapper;

    @Resource
    private PULRelationService likedService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    @Transactional
    public void transLikedMember2DB() {
        // 1. 首先先从redis集合中获取PSY_TEST_LIKED_MEMBER下面的所有keys
        Set<String> membersKey = stringRedisTemplate.keys(PSY_TEST_LIKED_USER + "*");
        if (ObjectUtil.isNull(membersKey)) {
            log.info("没有一个用户点了赞！");
            return;
        }

        // 2. 对于点过赞的用户，先全部删除，再批量插入，速度很快
        LambdaQueryWrapper<PULRelation> queryWrapper = new LambdaQueryWrapper<>();
        for (String memberKey: membersKey) {
            String userId = memberKey.split(":")[1];
            Set<String> testIds = stringRedisTemplate.opsForSet().members(PSY_TEST_LIKED_USER + userId);

            assert testIds != null;
            List<Long> testIdList = this.string2LongList(testIds);
            List<PULRelation> pulRelations = new ArrayList<>();
            for (Long testId: testIdList) {
                pulRelations.add(new PULRelation(Long.valueOf(userId), testId));
            }

            queryWrapper.eq(userId != null, PULRelation::getUserId, userId);
            // 全部删除
            likedService.remove(queryWrapper);
            // 再全部插入
            likedService.saveBatch(pulRelations);
        }
    }

    @Override
    @Transactional
    public void transLikedCount2DB() {
        // 1. 首先先从redis中获取PSY_TEST_LIKED_COUNT的 Hash 集合
        Map<Object, Object> likeCountMap = stringRedisTemplate.opsForHash().entries("PSY_TEST_LIKED_COUNT");
        // 2. 再批量更新数据库
        likedMapper.updateBatchByTestId(likeCountMap);
    }

    public List<Long> string2LongList(Set<String> members) {
        List<Long> membersL = new ArrayList<>();
        for (String member: members) {
            membersL.add(Long.valueOf(member));
        }
        return membersL;
    }

}
