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
import com.kci.moodstore.community.entity.Notification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * 通知表
 *
 * @author PlusL
 * @date 2023-04-16 16:02:55
 */
@Mapper
public interface NotificationMapper extends BaseMapper<Notification> {

	List<Map<String, Object>> selectByUserId(@Param("userId") Long userId, @Param("read") Boolean read, @Param
			("limit") Integer limit);

	// 查询未读消息数量
	@Select("select count(1) from notification where target_user_id = #{userId} and `read` = false")
	long countNotRead(@Param("userId") Long userId);

	// 将未读消息置为已读
	@Update("update notification set `read` = true where target_user_id = #{targetUserId}")
	void updateNotificationStatusToTrue(@Param("targetUserId") Long targetUserId);

}
