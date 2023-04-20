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

package com.kci.moodstore.community.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kci.moodstore.common.core.util.R;
import com.kci.moodstore.common.log.annotation.SysLog;
import com.kci.moodstore.community.entity.TopicTag;
import com.kci.moodstore.community.service.TopicTagService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;


/**
 * 标签话题对应关系表
 *
 * @author PlusL
 * @date 2023-04-16 16:02:55
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/topictag" )
@Tag(name = "标签话题对应关系表管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class TopicTagController {

    private final  TopicTagService topicTagService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param topicTag 标签话题对应关系表
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('community_topictag_get')" )
    public R getTopicTagPage(Page page, TopicTag topicTag) {
        return R.ok(topicTagService.page(page, Wrappers.query(topicTag)));
    }


    /**
     * 通过id查询标签话题对应关系表
     * @param tagId id
     * @return R
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{tagId}" )
    @PreAuthorize("@pms.hasPermission('community_topictag_get')" )
    public R getById(@PathVariable("tagId" ) Long tagId) {
        return R.ok(topicTagService.getById(tagId));
    }

    /**
     * 新增标签话题对应关系表
     * @param topicTag 标签话题对应关系表
     * @return R
     */
    @Operation(summary = "新增标签话题对应关系表", description = "新增标签话题对应关系表")
    @SysLog("新增标签话题对应关系表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('community_topictag_add')" )
    public R save(@RequestBody TopicTag topicTag) {
        return R.ok(topicTagService.save(topicTag));
    }

    /**
     * 修改标签话题对应关系表
     * @param topicTag 标签话题对应关系表
     * @return R
     */
    @Operation(summary = "修改标签话题对应关系表", description = "修改标签话题对应关系表")
    @SysLog("修改标签话题对应关系表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('community_topictag_edit')" )
    public R updateById(@RequestBody TopicTag topicTag) {
        return R.ok(topicTagService.updateById(topicTag));
    }

    /**
     * 通过id删除标签话题对应关系表
     * @param tagId id
     * @return R
     */
    @Operation(summary = "通过id删除标签话题对应关系表", description = "通过id删除标签话题对应关系表")
    @SysLog("通过id删除标签话题对应关系表" )
    @DeleteMapping("/{tagId}" )
    @PreAuthorize("@pms.hasPermission('community_topictag_del')" )
    public R removeById(@PathVariable Long tagId) {
        return R.ok(topicTagService.removeById(tagId));
    }

}
