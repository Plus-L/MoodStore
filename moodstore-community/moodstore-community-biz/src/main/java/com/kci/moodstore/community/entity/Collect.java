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
 * 收藏表
 *
 * @author PlusL
 * @date 2023-04-16 16:02:55
 */
@Data
@TableName("collect")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "收藏表")
public class Collect extends BaseEntity {

    /**
     * 收藏id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description ="收藏id")
    private Long id;

    /**
     * 话题id
     */
    @Schema(description ="话题id")
    private Long topicId;

    /**
     * userId
     */
    @Schema(description ="userId")
    private Long userId;


}
