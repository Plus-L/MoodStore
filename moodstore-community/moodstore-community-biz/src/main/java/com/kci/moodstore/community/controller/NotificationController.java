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
import com.kci.moodstore.common.security.util.SecurityUtils;
import com.kci.moodstore.community.entity.Notification;
import com.kci.moodstore.community.service.NotificationService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 通知表
 *
 * @author PlusL
 * @date 2023-04-16 16:02:55
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/notification" )
@Tag(name = "通知表管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class NotificationController {

    private final  NotificationService notificationService;

	@GetMapping("/notRead")
	public R notRead() {
		Long userId = SecurityUtils.getUser().getId();
		return R.ok(notificationService.countNotRead(userId));
	}

	@GetMapping("/markRead")
	public R markRead() {
		Long userId = SecurityUtils.getUser().getId();
		notificationService.markRead(userId);
		return R.ok();
	}

	// 通知列表
	@GetMapping("/list")
	public R list() {
		Long userId = SecurityUtils.getUser().getId();
		// 未读消息列表
		List<Map<String, Object>> notReadNotifications = notificationService.selectByUserId(userId, false, 20);
		// 已读消息列表
		List<Map<String, Object>> readNotifications = notificationService.selectByUserId(userId, true, 20);
		Map<String, Object> map = new HashMap<>();
		map.put("notRead", notReadNotifications);
		map.put("read", readNotifications);
		return R.ok(map);
	}


    // -------------------------------------- ADMIN ---------------------------------------

    /**
     * 分页查询
     * @param page 分页对象
     * @param notification 通知表
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('community_notification_get')" )
    public R getNotificationPage(Page page, Notification notification) {
        return R.ok(notificationService.page(page, Wrappers.query(notification)));
    }


    /**
     * 通过id查询通知表
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('community_notification_get')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(notificationService.getById(id));
    }

    /**
     * 新增通知表
     * @param notification 通知表
     * @return R
     */
    @Operation(summary = "新增通知表", description = "新增通知表")
    @SysLog("新增通知表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('community_notification_add')" )
    public R save(@RequestBody Notification notification) {
        return R.ok(notificationService.save(notification));
    }

    /**
     * 修改通知表
     * @param notification 通知表
     * @return R
     */
    @Operation(summary = "修改通知表", description = "修改通知表")
    @SysLog("修改通知表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('community_notification_edit')" )
    public R updateById(@RequestBody Notification notification) {
        return R.ok(notificationService.updateById(notification));
    }

    /**
     * 通过id删除通知表
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id删除通知表", description = "通过id删除通知表")
    @SysLog("通过id删除通知表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('community_notification_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(notificationService.removeById(id));
    }

}
