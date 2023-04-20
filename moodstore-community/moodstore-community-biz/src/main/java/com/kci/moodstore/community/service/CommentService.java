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
import com.kci.moodstore.community.entity.Comment;
import com.kci.moodstore.community.entity.Topic;
import com.kci.moodstore.community.entity.dto.CommentsByTopic;
import com.baomidou.mybatisplus.core.metadata.IPage;


import java.util.List;
import java.util.Map;

/**
 * 评论服务
 *
 * @author PlusL
 * @date 2023-04-16 16:02:55
 */
public interface CommentService extends IService<Comment> {

	// 根据话题id查询评论
	List<CommentsByTopic> selectByTopicId(Long topicId);

	// 删除话题时删除相关的评论
	void deleteByTopicId(Long topicId);

	// 根据用户id删除评论记录
	void deleteByUserId(Long userId);

	// 保存评论
	Comment insert(Comment comment);

	Comment selectById(Long id);

	Comment selectByTgMessageId(Long messageId);

	// 更新评论
	void update(Comment comment);

	// 对评论点赞
	int vote(Comment comment, Long userId);

	// 删除评论
	void delete(Comment comment);

	// 查询用户的评论
	IPage<Map<String, Object>> selectByUserId(Long userId, Page page);

	// 查询今天新增的话题数
	int countToday();

}
