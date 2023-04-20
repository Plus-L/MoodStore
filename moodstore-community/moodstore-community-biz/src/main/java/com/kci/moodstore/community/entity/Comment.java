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
 * 评论表
 *
 * @author PlusL
 * @date 2023-04-16 16:02:55
 */
@Data
@TableName("comment")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "评论表")
public class Comment extends BaseEntity {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description ="id")
    private Long id;

    /**
     * 评论类型：MD - MarkDown格式 | RICH - 富文本格式
     */
    @Schema(description ="评论类型：MD - MarkDown格式 | RICH - 富文本格式")
    private String style;

    /**
     * content
     */
    @Schema(description ="content")
    private String content;

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
     * commentId
     */
    @Schema(description ="commentId")
    private Long commentId;

    /**
     * 点赞的用户ID
     */
    @Schema(description ="点赞的用户ID")
    private String upIds;

    /**
     * tgMessageId
     */
    @Schema(description ="tgMessageId")
    private Long tgMessageId;

    /**
     * 状态：true - 审核通过 | false - 审核中
     */
    @Schema(description ="状态：true - 审核通过 | false - 审核中")
    private Boolean status;


}
