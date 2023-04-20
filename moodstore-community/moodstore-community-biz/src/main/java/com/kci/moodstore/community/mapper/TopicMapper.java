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
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kci.moodstore.community.entity.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;


import java.util.Map;

/**
 * 话题表
 *
 * @author PlusL
 * @date 2023-04-16 16:02:55
 */
@Mapper
public interface TopicMapper extends BaseMapper<Topic> {

	IPage<Map<String, Object>> selectAll(Page<Map<String, Object>> page, @Param("tab") String tab);

	IPage<Map<String, Object>> selectByTag(Page<Map<String, Object>> page, @Param("tag") String tag);

	IPage<Map<String, Object>> selectByUserId(Page<Map<String, Object>> page, @Param("userId") Long userId);

	IPage<Map<String, Object>> selectAllForAdmin(Page<Map<String, Object>> page, @Param("startDate") String
			startDate, @Param("endDate") String endDate, @Param("username") String username);

	int countToday();

	IPage<Map<String, Object>> search(Page<Map<String, Object>> page, @Param("keyword") String keyword);

}
