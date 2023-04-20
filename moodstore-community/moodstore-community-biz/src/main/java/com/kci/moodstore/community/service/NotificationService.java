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

import com.baomidou.mybatisplus.extension.service.IService;
import com.kci.moodstore.community.entity.Notification;

import java.util.List;
import java.util.Map;

/**
 * 通知表
 *
 * @author PlusL
 * @date 2023-04-16 16:02:55
 */
public interface NotificationService extends IService<Notification> {

	// 查询消息
	List<Map<String, Object>> selectByUserId(Long userId, Boolean read, Integer limit);

	void markRead(Long userId);

	// 查询未读消息数量
	long countNotRead(Long userId);

	void deleteByTopicId(Long topicId);

	void deleteByUserId(Long userId);

	void insert(Long userId, Long targetUserId, Long topicId, String action, String content);

}
