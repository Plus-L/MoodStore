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
import com.kci.moodstore.community.entity.MyTag;
import com.baomidou.mybatisplus.core.metadata.IPage;


import java.util.List;
import java.util.Map;

/**
 * 标签表
 *
 * @author PlusL
 * @date 2023-04-16 16:02:55
 */
public interface TagService extends IService<MyTag> {
	void selectMyTagsByTopicId(Page<Map<String, Object>> page);

	MyTag selectById(Long id);

	MyTag selectByName(String name);

	List<MyTag> selectByIds(List<Long> ids);

	// 根据话题查询关联的所有标签
	List<MyTag> selectByTopicId(Long topicId);

	// 将创建话题时填的MyTag处理并保存
	List<MyTag> insertMyTag(String newMyTags);

	// 将标签的话题数都-1
	void reduceTopicCount(Long id);

	// 查询标签关联的话题
	IPage<Map<String, Object>> selectTopicByMyTagId(Long MyTagId, Page page);

	// 查询标签列表
	IPage<MyTag> selectAll(Page page, String name);

	void update(MyTag MyTag);

	// 如果 topic_MyTag 表里还有关联的数据，这里删除会报错
	void delete(Long id);

	//同步标签的话题数
	void async();

	// 查询今天新增的标签数
	int countToday();
}
