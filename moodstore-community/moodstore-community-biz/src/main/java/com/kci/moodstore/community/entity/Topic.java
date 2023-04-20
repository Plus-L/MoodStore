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

import java.time.LocalDateTime;

/**
 * 话题表
 *
 * @author PlusL
 * @date 2023-04-16 16:02:55
 */
@Data
@TableName("topic")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "话题表")
public class Topic extends BaseEntity {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description ="id")
    private Long id;

    /**
     * 标题
     */
    @Schema(description ="title")
    private String title;

    /**
     * 内容
     */
    @Schema(description ="content")
    private String content;

    /**
     * 修改时间
     */
    @Schema(description ="modifyTime")
    private LocalDateTime modifyTime;

    /**
     * 用户ID
     */
    @Schema(description ="userId")
    private Long userId;

    /**
     * 文本类型
     */
    @Schema(description ="style")
    private String style;

    /**
     * 评论数
     */
    @Schema(description ="commentCount")
    private Integer commentCount;

    /**
     * 收藏数
     */
    @Schema(description ="collectCount")
    private Integer collectCount;

    /**
     * 观看数
     */
    @Schema(description ="view")
    private Integer view;

    /**
     * 置顶
     */
    @Schema(description ="top")
    private Boolean top;

    /**
     * 精选
     */
    @Schema(description ="good")
    private Boolean good;

    /**
     * upIds
     */
    @Schema(description ="upIds")
    private String upIds;


}
