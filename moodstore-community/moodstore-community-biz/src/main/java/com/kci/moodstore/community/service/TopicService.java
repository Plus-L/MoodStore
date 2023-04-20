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

package com.kci.moodstore.community.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kci.moodstore.community.entity.Topic;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.Map;

/**
 * 话题表
 *
 * @author PlusL
 * @date 2023-04-16 16:02:55
 */
public interface TopicService extends IService<Topic> {
	// 搜索
	IPage<Map<String, Object>> search(Page page, String keyword);

	// 分页查询话题
	IPage<Map<String, Object>> selectAll(Page page, String tab);

	// 查询话题作者其它的话题
	List<Topic> selectAuthorOtherTopic(Long userId, Long topicId, Long limit);

	// 查询用户的话题
	IPage<Map<String, Object>> selectByUserId(Page page, Long userId);

	// 保存话题
	Integer insert(Topic topic);

	// 根据title查询话题，防止重复话题
	Topic selectByTitle(String title);

	// 处理话题的访问量
	Topic updateViewCount(Topic topic, String ip);

	// 更新话题
	void updateTopic(Topic topic, String tags);

	// 删除话题
	void delete(Topic topic);

	// 根据用户id删除帖子
	void deleteByUserId(Long userId);

	IPage<Map<String, Object>> selectAllForAdmin(Page page, String startDate, String endDate, String username);

	// 查询今天新增的话题数
	int countToday();

	int vote(Topic topic, Long userId);
}
