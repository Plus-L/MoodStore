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
import com.kci.moodstore.community.entity.SensitiveWord;
import com.kci.moodstore.community.service.SensitiveWordService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;


/**
 * 敏感词表
 *
 * @author PlusL
 * @date 2023-04-16 16:02:55
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/sensitiveword" )
@Tag(name = "敏感词表管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class SensitiveWordController {

    private final  SensitiveWordService sensitiveWordService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param sensitiveWord 敏感词表
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('community_sensitiveword_get')" )
    public R getSensitiveWordPage(Page page, SensitiveWord sensitiveWord) {
        return R.ok(sensitiveWordService.page(page, Wrappers.query(sensitiveWord)));
    }


    /**
     * 通过id查询敏感词表
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('community_sensitiveword_get')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(sensitiveWordService.getById(id));
    }

    /**
     * 新增敏感词表
     * @param sensitiveWord 敏感词表
     * @return R
     */
    @Operation(summary = "新增敏感词表", description = "新增敏感词表")
    @SysLog("新增敏感词表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('community_sensitiveword_add')" )
    public R save(@RequestBody SensitiveWord sensitiveWord) {
        return R.ok(sensitiveWordService.save(sensitiveWord));
    }

    /**
     * 修改敏感词表
     * @param sensitiveWord 敏感词表
     * @return R
     */
    @Operation(summary = "修改敏感词表", description = "修改敏感词表")
    @SysLog("修改敏感词表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('community_sensitiveword_edit')" )
    public R updateById(@RequestBody SensitiveWord sensitiveWord) {
        return R.ok(sensitiveWordService.updateById(sensitiveWord));
    }

    /**
     * 通过id删除敏感词表
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id删除敏感词表", description = "通过id删除敏感词表")
    @SysLog("通过id删除敏感词表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('community_sensitiveword_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(sensitiveWordService.removeById(id));
    }

}
