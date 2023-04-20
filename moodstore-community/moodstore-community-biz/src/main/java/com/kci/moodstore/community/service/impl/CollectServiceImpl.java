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
import com.kci.moodstore.community.entity.Collect;
import com.kci.moodstore.community.entity.Topic;
import com.kci.moodstore.community.mapper.CollectMapper;
import com.kci.moodstore.community.mapper.TopicMapper;
import com.kci.moodstore.community.service.CollectService;
import com.kci.moodstore.community.service.NotificationService;
import com.kci.moodstore.community.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收藏表
 *
 * @author PlusL
 * @date 2023-04-16 16:02:55
 */
@Service
@RequiredArgsConstructor
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {

	private final TopicMapper topicMapper;

	private final NotificationService notificationService;

	@Override
	public List<Collect> selectByTopicId(Long topicId) {
		QueryWrapper<Collect> wrapper = new QueryWrapper<>();
		wrapper.lambda().eq(Collect::getTopicId, topicId);
		return baseMapper.selectList(wrapper);
	}

	@Override
	public Collect selectByTopicIdAndUserId(Long topicId, Long userId) {
		QueryWrapper<Collect> wrapper = new QueryWrapper<>();
		wrapper.lambda().eq(Collect::getTopicId, topicId).eq(Collect::getUserId, userId);
		List<Collect> collects = baseMapper.selectList(wrapper);
		if (collects.size() > 0) return collects.get(0);
		return null;
	}

	@Override
	public Collect insert(Long topicId, Long userId) {
		Collect collect = new Collect();
		collect.setTopicId(topicId);
		collect.setUserId(userId);
		baseMapper.insert(collect);

		// 通知
		Topic topic = topicMapper.selectById(topicId);
		topic.setCollectCount(topic.getCollectCount() + 1);
		topicMapper.update(topic, null);
		// 收藏自己的话题不发通知
		if (!userId.equals(topic.getUserId())) {
			String emailTitle = "你的话题 %s 被 %s 收藏了，快去看看吧！";
			notificationService.insert(userId, topic.getUserId(), topicId, "COLLECT", emailTitle);
			// 发送邮件通知 DEPRECATED 不再发送邮件通知
			// 如果开启了websocket，就发网页通知
/*			if (systemConfigService.selectAllConfig().get("websocket").toString().equals("1")) {
				MyWebSocket.emit(topic.getUserId(), new Message("notifications", String.format(emailTitle, topic.getTitle(),
						user.getUsername())));
			}*/

		}

		return collect;
	}

	@Override
	public void delete(Long topicId, Long userId) {
		QueryWrapper<Collect> wrapper = new QueryWrapper<>();
		wrapper.lambda().eq(Collect::getTopicId, topicId).eq(Collect::getUserId, userId);
		baseMapper.delete(wrapper);
		// 对话题中冗余的collectCount字段收藏数量-1
		Topic topic = topicMapper.selectById(topicId);
		topic.setCollectCount(topic.getCollectCount() - 1);
		topicMapper.update(topic, null);
	}

	@Override
	public void deleteByTopicId(Long topicId) {
		QueryWrapper<Collect> wrapper = new QueryWrapper<>();
		wrapper.lambda().eq(Collect::getTopicId, topicId);
		baseMapper.delete(wrapper);
	}

	@Override
	public void deleteByUserId(Long userId) {
		QueryWrapper<Collect> wrapper = new QueryWrapper<>();
		wrapper.lambda().eq(Collect::getUserId, userId);
		baseMapper.delete(wrapper);
	}

	@Override
	public Long countByUserId(Long userId) {
		QueryWrapper<Collect> wrapper = new QueryWrapper<>();
		wrapper.lambda().eq(Collect::getUserId, userId);
		return baseMapper.selectCount(wrapper);
	}
}
