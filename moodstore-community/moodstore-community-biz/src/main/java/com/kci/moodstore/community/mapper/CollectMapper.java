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

package com.kci.moodstore.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kci.moodstore.community.entity.Collect;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 收藏表
 *
 * @author PlusL
 * @date 2023-04-16 16:02:55
 */
@Mapper
public interface CollectMapper extends BaseMapper<Collect> {

	/**
	 * 根据topicId查询收藏
	 * @param topicId 话题ID
	 * @return 收藏列表
	 */
	List<Collect> selectByTopicId(Integer topicId);

	/**
	 * 查询单一用户是否收藏该话题
	 * @param topicId 话题Id
	 * @param userId 用户ID
	 * @return 收藏
	 */
	Collect selectByTopicIdAndUserId(Integer topicId, Integer userId);


	/**
	 * 查询该用户的收藏数
	 * @param userId 用户ID
	 * @return 是否成功
	 */
	int countByUserId(Integer userId);

}
