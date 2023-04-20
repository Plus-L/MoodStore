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
import com.kci.moodstore.community.entity.MyTag;
import com.kci.moodstore.community.entity.TopicTag;

import java.util.List;

/**
 * 标签话题对应关系表
 *
 * @author PlusL
 * @date 2023-04-16 16:02:55
 */
public interface TopicTagService extends IService<TopicTag> {

	List<TopicTag> selectByTopicId(Long topicId);

	List<TopicTag> selectByTagId(Long tagId);

	void insertTopicTag(Long topicId, List<MyTag> tagList);

	// 删除话题所有关联的标签记录
	void deleteByTopicId(Long id);

}
