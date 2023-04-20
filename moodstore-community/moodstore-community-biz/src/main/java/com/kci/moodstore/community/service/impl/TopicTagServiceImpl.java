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
import com.kci.moodstore.community.entity.MyTag;
import com.kci.moodstore.community.entity.TopicTag;
import com.kci.moodstore.community.mapper.TopicTagMapper;
import com.kci.moodstore.community.service.TopicTagService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 标签话题对应关系表
 *
 * @author PlusL
 * @date 2023-04-16 16:02:55
 */
@Service
public class TopicTagServiceImpl extends ServiceImpl<TopicTagMapper, TopicTag> implements TopicTagService {

	@Override
	public List<TopicTag> selectByTopicId(Long topicId) {
		QueryWrapper<TopicTag> wrapper = new QueryWrapper<>();
		wrapper.lambda().eq(TopicTag::getTopicId, topicId);
		return baseMapper.selectList(wrapper);
	}

	@Override
	public List<TopicTag> selectByTagId(Long tagId) {
		QueryWrapper<TopicTag> wrapper = new QueryWrapper<>();
		wrapper.lambda().eq(TopicTag::getTagId, tagId);
		return baseMapper.selectList(wrapper);
	}

	@Override
	public void insertTopicTag(Long topicId, List<MyTag> tagList) {
		//TODO: 为何删除？
		// 先删除topicId对应的所有记录
		this.deleteByTopicId(topicId);
		// 循环保存对应关联
		tagList.forEach(tag -> {
			TopicTag topicTag = new TopicTag();
			topicTag.setTopicId(topicId);
			topicTag.setTagId(tag.getId());
			baseMapper.insert(topicTag);
		});
	}

	@Override
	public void deleteByTopicId(Long id) {
		QueryWrapper<TopicTag> wrapper = new QueryWrapper<>();
		wrapper.lambda().eq(TopicTag::getTopicId, id);
		baseMapper.delete(wrapper);
	}
}
