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
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kci.moodstore.common.core.constant.CommonConstants;
import com.kci.moodstore.community.entity.Collect;
import com.kci.moodstore.community.entity.MyTag;
import com.kci.moodstore.community.entity.Topic;
import com.kci.moodstore.community.mapper.TopicMapper;
import com.kci.moodstore.community.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 话题表
 *
 * @author PlusL
 * @date 2023-04-16 16:02:55
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements TopicService {

	private final TagService tagService;

	private final TopicTagService topicTagService;

	private final NotificationService notificationService;

	private final CollectService collectService;

	private final CommentService commentService;

	@Override
	public IPage<Map<String, Object>> search(Page page, String keyword) {
		return baseMapper.search(page, keyword);
	}

	@Override
	public IPage<Map<String, Object>> selectAll(Page page, String tab) {
		return baseMapper.selectAll(page, tab);
	}

	@Override
	public List<Topic> selectAuthorOtherTopic(Long userId, Long topicId, Long limit) {
		//MyBatis-Plus还没用熟练
		QueryWrapper<Topic> wrapper = new QueryWrapper<>();
		wrapper.eq("user_id", userId).orderByDesc("create_time");
		if (topicId != null) {
			wrapper.lambda().ne(Topic::getId, topicId);
		}
		if (limit != null) wrapper.last("limit " + limit);
		return baseMapper.selectList(wrapper);
	}

	@Override
	public IPage<Map<String, Object>> selectByUserId(Page page, Long userId) {
		IPage<Map<String, Object>> iPage = baseMapper.selectByUserId(page, userId);
		//TODO: 敏感词过滤待处理
		return iPage;
	}

	@Override
	public Integer insert(Topic topic) {
		//TODO: 积分模块待处理
		return baseMapper.insert(topic);
	}


	@Override
	public Topic selectByTitle(String title) {
		QueryWrapper<Topic> wrapper = new QueryWrapper<>();
		wrapper.lambda().eq(Topic::getTitle, title);
		return baseMapper.selectOne(wrapper);
	}

	@Override
	public Topic updateViewCount(Topic topic, String ip) {
		topic.setView(topic.getView() + 1);
		this.updateTopic(topic, null);
		return topic;
	}

	@Override
	public void updateTopic(Topic topic, String tags) {
		baseMapper.updateById(topic);
		// 处理标签
		if (!StringUtils.isEmpty(tags)) {
			// 旧标签每个topicCount都-1
			//TODO: 处理Tag
			tagService.reduceTopicCount(topic.getId());
			if (!StringUtils.isEmpty(tags)) {
				// 保存标签
				List<MyTag> tagList = tagService.insertMyTag(Jsoup.clean(tags, Whitelist.none()));
				// 处理标签与话题的关联
				topicTagService.insertTopicTag(topic.getId(), tagList);
			}
		}
		// 索引话题
	}

	@Override
	public void delete(Topic topic) {
		Long id = topic.getId();
		//TODO: 删除处仍需处理
		// 删除相关通知
		notificationService.deleteByTopicId(id);
		// 删除相关收藏
		collectService.deleteByTopicId(id);
		// 删除相关的评论
		commentService.deleteByTopicId(id);
		// 将话题对应的标签 topicCount -1
		tagService.reduceTopicCount(id);
		// 删除相应的关联标签
		topicTagService.deleteByTopicId(id);
		// TODO: 减去用户积分

		// 最后删除话题
		baseMapper.deleteById(id);
	}

	@Override
	public void deleteByUserId(Long userId) {
		QueryWrapper<Topic> wrapper = new QueryWrapper<>();
		wrapper.lambda().eq(Topic::getUserId, userId);
		List<Topic> topics = baseMapper.selectList(wrapper);
		topics.forEach(topic -> {
			// 删除话题相关联的评论
			commentService.deleteByTopicId(topic.getId());
			// 删除被收藏的话题记录
			collectService.deleteByTopicId(topic.getId());
			// 删除关联标签及标签统计数据
			// 旧标签每个topicCount都-1
			tagService.reduceTopicCount(topic.getId());
			// 删除话题关联的标签中间表数据
			topicTagService.deleteByTopicId(topic.getId());
		});
		//删除话题
		baseMapper.delete(wrapper);
	}

	@Override
	public IPage<Map<String, Object>> selectAllForAdmin(Page page, String startDate, String endDate, String username) {
		return baseMapper.selectAllForAdmin(page, startDate, endDate, username);
	}

	@Override
	public int countToday() {
		return baseMapper.countToday();
	}

	@Override
	public int vote(Topic topic, Long userId) {
		String upIds = topic.getUpIds();
		// 将点赞用户id的字符串转成集合
		Set<String> strings = org.springframework.util.StringUtils.commaDelimitedListToSet(upIds);
		// 把新的点赞用户id添加进集合，这里用set，正好可以去重，如果集合里已经有用户的id了，那么这次动作被视为取消点赞

		if (strings.contains(String.valueOf(userId))) { // 取消点赞行为
			strings.remove(String.valueOf(userId));
		} else { // 点赞行为
			strings.add(String.valueOf(userId));
		}
		// 再把这些id按逗号隔开组成字符串
		topic.setUpIds(org.springframework.util.StringUtils.collectionToCommaDelimitedString(strings));
		// 更新评论
		this.updateTopic(topic, null);

		return strings.size();
	}
}
