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
package com.kci.moodstore.community.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kci.moodstore.common.mybatis.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 通知表
 *
 * @author PlusL
 * @date 2023-04-16 16:02:55
 */
@Data
@TableName("notification")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "通知表")
public class Notification extends BaseEntity {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description ="id")
    private Long id;

    /**
     * topicId
     */
    @Schema(description ="topicId")
    private Long topicId;

    /**
     * userId
     */
    @Schema(description ="userId")
    private Long userId;

    /**
     * 投送目标用户ID
     */
    @Schema(description ="投送目标用户ID")
    private Long targetUserId;

    /**
     * 动作: REPLY - 回复 | COMMENT - 评论 | COLLECT - 收藏 | TOPIC_UP - 置顶主题 | COMMENT_UP - 置顶评论
     */
    @Schema(description ="动作: REPLY - 回复 | COMMENT - 评论 | COLLECT - 收藏 | TOPIC_UP - 置顶主题 | COMMENT_UP - 置顶评论")
    private String action;

    /**
     * 是否已读
     */
    @Schema(description ="是否已读")
    private Boolean read;

    /**
     * content
     */
    @Schema(description ="content")
    private String content;


}
