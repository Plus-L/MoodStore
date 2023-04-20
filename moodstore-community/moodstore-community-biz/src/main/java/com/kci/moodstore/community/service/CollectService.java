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
import com.kci.moodstore.admin.api.vo.UserVO;
import com.kci.moodstore.community.entity.Collect;

import java.util.List;
import java.util.Map;

/**
 * 收藏表
 *
 * @author PlusL
 * @date 2023-04-16 16:02:55
 */
public interface CollectService extends IService<Collect> {

	/**
	 * 根据topicId查询收藏
	 * @param topicId 话题ID
	 * @return 收藏列表
	 */
	List<Collect> selectByTopicId(Long topicId);

	/**
	 * 查询单一用户是否收藏该话题
	 * @param topicId 话题Id
	 * @param userId 用户ID
	 * @return 收藏
	 */
	Collect selectByTopicIdAndUserId(Long topicId, Long userId);

	/**
	 * 新增收藏，这里同时处理话题拥有者通知信息
	 * @param topicId
	 * @param userId
	 * @return
	 */
	Collect insert(Long topicId, Long userId);

	/**
	 * 删除（取消）收藏
	 * @param topicId 话题Id
	 * @param userId 用户ID
	 */
	void delete(Long topicId, Long userId);

	/**
	 * 根据话题id删除收藏记录
	 * @param topicId 话题ID
	 */
	void deleteByTopicId(Long topicId);

	/**
	 * 删除该用户的所有收藏
	 * @param userId 用户ID
	 */
	void deleteByUserId(Long userId);

	/**
	 * 查询该用户的收藏数
	 * @param userId 用户ID
	 * @return 是否成功
	 */
	Long countByUserId(Long userId);


}
