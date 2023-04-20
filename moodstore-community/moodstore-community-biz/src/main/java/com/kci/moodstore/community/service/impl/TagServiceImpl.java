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
import com.kci.moodstore.community.entity.MyTag;
import com.kci.moodstore.community.entity.TopicTag;
import com.kci.moodstore.community.mapper.TagMapper;
import com.kci.moodstore.community.service.TagService;
import com.kci.moodstore.community.service.TopicTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 标签表
 *
 * @author PlusL
 * @date 2023-04-16 16:02:55
 */
@Service
@RequiredArgsConstructor
public class TagServiceImpl extends ServiceImpl<TagMapper, MyTag> implements TagService {

	private final TopicTagService topicTagService;

	@Override
	public void selectMyTagsByTopicId(Page<Map<String, Object>> page) {
		page.getRecords().forEach(map -> {
			List<TopicTag> topicTags = topicTagService.selectByTopicId((Long) map.get("id"));
			if (!topicTags.isEmpty()) {
				List<Long> tagIds = topicTags.stream().map(TopicTag::getTagId).collect(Collectors.toList());
				List<MyTag> tags = this.selectByIds(tagIds);
				map.put("tags", tags);
			}
		});
	}

	@Override
	public MyTag selectById(Long id) {
		return baseMapper.selectById(id);
	}

	@Override
	public MyTag selectByName(String name) {
		QueryWrapper<MyTag> wrapper = new QueryWrapper<>();
		wrapper.lambda().eq(MyTag::getName, name);
		return baseMapper.selectOne(wrapper);
	}

	@Override
	public List<MyTag> selectByIds(List<Long> ids) {
		QueryWrapper<MyTag> wrapper = new QueryWrapper<>();
		wrapper.lambda().in(MyTag::getId, ids);
		return baseMapper.selectList(wrapper);
	}

	@Override
	public List<MyTag> selectByTopicId(Long topicId) {
		List<TopicTag> topicTags = topicTagService.selectByTopicId(topicId);
		if (!topicTags.isEmpty()) {
			List<Long> tagIds = topicTags.stream().map(TopicTag::getTagId).collect(Collectors.toList());
			QueryWrapper<MyTag> wrapper = new QueryWrapper<>();
			wrapper.lambda().in(MyTag::getId, tagIds);
			return baseMapper.selectList(wrapper);
		}
		return Collections.emptyList();
	}

	@Override
	public List<MyTag> insertMyTag(String newMyTags) {
		// 使用工具将字符串按逗号分隔成数组
		String[] _tags = StringUtils.commaDelimitedListToStringArray(newMyTags);
		List<MyTag> tagList = new ArrayList<>();
		for (String _tag : _tags) {
			MyTag tag = this.selectByName(_tag);
			if (tag == null) {
				tag = new MyTag();
				tag.setName(_tag);
				tag.setTopicCount(1);
				baseMapper.insert(tag);
			} else {
				tag.setTopicCount(tag.getTopicCount() + 1);
				baseMapper.updateById(tag);
			}
			tagList.add(tag);
		}
		return tagList;
	}

	@Override
	public void reduceTopicCount(Long id) {
		List<MyTag> tags = this.selectByTopicId(id);
		tags.forEach(tag -> {
			tag.setTopicCount(tag.getTopicCount() - 1);
			baseMapper.updateById(tag);
		});
	}

	@Override
	public IPage<Map<String, Object>> selectTopicByMyTagId(Long myTagId, Page page) {
		return baseMapper.selectTopicByTagId(page, myTagId);
	}

	@Override
	public IPage<MyTag> selectAll(Page page, String name) {
		QueryWrapper<MyTag> wrapper = new QueryWrapper<>();
		// 当传进来的name不为null的时候，就根据name查询
		if (!StringUtils.isEmpty(name)) {
			wrapper.lambda().eq(MyTag::getName, name);
		}
		wrapper.orderByDesc("topic_count");
		return baseMapper.selectPage(page, wrapper);
	}

	@Override
	public void update(MyTag myTag) {
		baseMapper.updateById(myTag);
	}

	@Override
	public void delete(Long id) {
		baseMapper.deleteById(id);
	}

	@Override
	public void async() {
		List<MyTag> tags = baseMapper.selectList(null);
		tags.forEach(tag -> {
			List<TopicTag> topicTags = topicTagService.selectByTagId(tag.getId());
			tag.setTopicCount(topicTags.size());
			this.update(tag);
		});
	}

	@Override
	public int countToday() {
		return baseMapper.countToday();
	}
}
