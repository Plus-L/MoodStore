package com.kci.moodstore.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson2.JSON;
import com.kci.moodstore.framework.cache.util.RedisKeyUtil;
import com.kci.moodstore.framework.cache.util.RedisUtil;
import com.kci.moodstore.framework.database.dto.PageDTO;
import com.kci.moodstore.framework.database.util.PageUtil;
import com.kci.moodstore.framework.database.vo.PageVO;
import com.kci.moodstore.user.dto.UserDTO;
import com.kci.moodstore.user.mapper.UserMapper;
import com.kci.moodstore.user.mapstruct.UserMapStruct;
import com.kci.moodstore.user.model.User;
import com.kci.moodstore.user.service.UserService;
import com.kci.moodstore.user.vo.UserSimpleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @program: moodstore
 * @description: 用户服务实现类
 * @author: PlusL
 * @create: 2022-10-19 18:24
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public UserDTO getUserById(Long id) {
        User user = getCache(id);
        if (BeanUtil.isEmpty(user)) {
            user = initCache(id);
        }
        return UserMapStruct.INSTANCE.toDTO(user);
    }

    @Override
    public PageVO<UserSimpleVO> getUserInPage(PageDTO pageDTO) {
        return PageUtil.doPage(pageDTO, () -> userMapper.list());
    }

    @Override
    public List<UserSimpleVO> getUserByUserIds(List<Long> userIds) {
        if (CollUtil.isEmpty(userIds)) {
            return new ArrayList<>();
        }
        return UserMapStruct.INSTANCE.toSimpleVOList(userMapper.getUsersByIds(userIds));
    }

    /**
     * 优先从缓存中取值
     * @param userId
     * @return
     */
    private User getCache(Long userId) {
        String redisKey = RedisKeyUtil.getUserKey(userId);
        return JSON.parseObject(redisUtil.get(redisKey), User.class);
//        return (User) redisTemplate.opsForValue().get(redisKey);
    }

    /**
     * 缓存中没有该用户信息时，则将其存入缓存
     * @param userId
     * @return
     */
    private User initCache(Long userId) {
        User user = userMapper.getUserById(userId);
        String redisKey = RedisKeyUtil.getUserKey(userId);
//        redisTemplate.opsForValue().set(redisKey, user, 3600, TimeUnit.SECONDS);
        redisUtil.set(redisKey, user, 3600L);
        return user;
    }

    /**
     * 用户信息变更时清除对应缓存数据
     * @param userId
     */
    private void clearCache(Long userId) {
        String redisKey = RedisKeyUtil.getUserKey(userId);
        redisTemplate.delete(redisKey);
    }

}
