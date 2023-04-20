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

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kci.moodstore.common.core.util.R;
import com.kci.moodstore.common.log.annotation.SysLog;
import com.kci.moodstore.common.security.service.MoodstoreUser;
import com.kci.moodstore.common.security.util.SecurityUtils;
import com.kci.moodstore.community.entity.Collect;
import com.kci.moodstore.community.service.CollectService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;


/**
 * 收藏表
 *
 * @author PlusL
 * @date 2023-04-16 16:02:55
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/collect" )
@Tag(name = "收藏管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class CollectController {

    private final  CollectService collectService;

	@PostMapping("/create/{topicId}")
	@Operation(summary = "用户收藏话题", description = "用户收藏话题")
	@SysLog("用户收藏话题")
	@PreAuthorize("@pms.hasPermission('community_collect_add')" )
	public R doCollect(@PathVariable Long topicId) {
		MoodstoreUser user = SecurityUtils.getUser();
		Collect collect = collectService.selectByTopicIdAndUserId(topicId, user.getId());
		Assert.isNull(collect, "每人每个话题只能收藏一次哦！");
		collectService.insert(topicId, user.getId());
		return R.ok();
	}

	// 取消收藏
	@DeleteMapping("/delete/{topicId}")
	@Operation(summary = "用户取消收藏", description = "用户取消收藏")
	@SysLog("用户取消收藏")
	public R cancelCollect(@PathVariable Long topicId) {
		MoodstoreUser user = SecurityUtils.getUser();
		Collect collect = collectService.selectByTopicIdAndUserId(topicId, user.getId());
		Assert.notNull(collect, "你都没有收藏这个话题，无法取消");
		collectService.delete(topicId, user.getId());
		return R.ok();
	}

    // ---------------------------------- ADMIN --------------------------------------
    /**
     * 分页查询
     * @param page 分页对象
     * @param collect 收藏表
     * @return 分页查询结果
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('community_collect_get')" )
    public R getCollectPage(Page page, Collect collect) {
        return R.ok(collectService.page(page, Wrappers.query(collect)));
    }


    /**
     * 通过id查询收藏表
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('community_collect_get')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(collectService.getById(id));
    }

    /**
     * 新增收藏表
     * @param collect 收藏表
     * @return R
     */
    @Operation(summary = "新增收藏", description = "新增收藏")
    @SysLog("新增收藏" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('community_collect_add')" )
    public R save(@RequestBody Collect collect) {
        return R.ok(collectService.save(collect));
    }

    /**
     * 修改收藏表
     * @param collect 收藏表
     * @return R
     */
    @Operation(summary = "修改收藏", description = "修改收藏")
    @SysLog("修改收藏" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('community_collect_edit')" )
    public R updateById(@RequestBody Collect collect) {
        return R.ok(collectService.updateById(collect));
    }

    /**
     * 通过id删除收藏表
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id删除收藏", description = "通过id删除收藏")
    @SysLog("通过id删除收藏" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('community_collect_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(collectService.removeById(id));
    }

}
