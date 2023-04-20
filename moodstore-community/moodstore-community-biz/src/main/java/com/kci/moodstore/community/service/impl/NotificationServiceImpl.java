/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the moodstore4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */
package com.kci.moodstore.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kci.moodstore.community.entity.Notification;
import com.kci.moodstore.community.mapper.NotificationMapper;
import com.kci.moodstore.community.service.NotificationService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 通知表
 *
 * @author PlusL
 * @date 2023-04-16 16:02:55
 */
@Service
public class NotificationServiceImpl extends ServiceImpl<NotificationMapper, Notification> implements NotificationService {


	@Override
	public List<Map<String, Object>> selectByUserId(Long userId, Boolean read, Integer limit) {
		return baseMapper.selectByUserId(userId, read, limit);
	}

	@Override
	public void markRead(Long userId) {
		baseMapper.updateNotificationStatusToTrue(userId);
	}

	@Override
	public long countNotRead(Long userId) {
		return baseMapper.countNotRead(userId);
	}

	@Override
	public void deleteByTopicId(Long topicId) {
		QueryWrapper<Notification> wrapper = new QueryWrapper<>();
		wrapper.lambda().eq(Notification::getTopicId, topicId);
		baseMapper.delete(wrapper);
	}

	@Override
	public void deleteByUserId(Long userId) {
		QueryWrapper<Notification> wrapper = new QueryWrapper<>();
		wrapper.lambda().eq(Notification::getTargetUserId, userId).or().eq(Notification::getUserId, userId);
		baseMapper.delete(wrapper);
	}

	@Override
	public void insert(Long userId, Long targetUserId, Long topicId, String action, String content) {
		Notification notification = new Notification();
		notification.setAction(action);
		notification.setContent(content);
		notification.setUserId(userId);
		notification.setTargetUserId(targetUserId);
		notification.setTopicId(topicId);
		notification.setRead(false);
		baseMapper.insert(notification);
	}
}
