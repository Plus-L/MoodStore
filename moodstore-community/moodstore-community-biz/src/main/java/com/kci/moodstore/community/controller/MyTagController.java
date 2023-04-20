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
import com.kci.moodstore.community.entity.MyTag;
import com.kci.moodstore.community.service.TagService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;


/**
 * 标签表  --  Tag 修改为  MyTag 因为 Tag 与 Swagger 的一个组件名字冲突
 *
 * @author PlusL
 * @date 2023-04-16 16:02:55
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/tag" )
@Tag(name = "标签管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class MyTagController {

    private final TagService tagService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param myTag 标签表
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('community_tag_get')" )
    public R getTagPage(Page page, MyTag myTag) {
        return R.ok(tagService.page(page, Wrappers.query(myTag)));
    }


    /**
     * 通过id查询标签表
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('community_tag_get')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(tagService.getById(id));
    }

    /**
     * 新增标签表
     * @param myTag 标签表
     * @return R
     */
    @Operation(summary = "新增标签", description = "新增标签")
    @SysLog("新增标签" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('community_tag_add')" )
    public R save(@RequestBody MyTag myTag) {
        return R.ok(tagService.save(myTag));
    }

    /**
     * 修改标签表
     * @param myTag 标签表
     * @return R
     */
    @Operation(summary = "修改标签", description = "修改标签")
    @SysLog("修改标签" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('community_tag_edit')" )
    public R updateById(@RequestBody MyTag myTag) {
        return R.ok(tagService.updateById(myTag));
    }

    /**
     * 通过id删除标签表
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id删除标签", description = "通过id删除标签")
    @SysLog("通过id删除标签" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('community_tag_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(tagService.removeById(id));
    }

}
