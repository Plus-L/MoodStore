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
import com.kci.moodstore.community.entity.Comment;
import com.kci.moodstore.community.entity.Topic;
import com.kci.moodstore.community.service.CommentService;
import com.kci.moodstore.community.service.TopicService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;


/**
 * 评论表
 *
 * @author PlusL
 * @date 2023-04-16 16:02:55
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment" )
@Tag(name = "评论管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class CommentController {

    private final  CommentService commentService;

    private final TopicService topicService;

	// 创建评论
	@PostMapping("/create")
	@Operation(summary = "新增评论", description = "新增评论")
	@SysLog("新增评论" )
	@PreAuthorize("@pms.hasPermission('community_comment_add')" )
	public R create(@RequestBody Comment comment) {
		return R.ok(commentService.insert(comment));
	}

	// 更新评论
	@PutMapping("/update/{id}")
	public R update(@PathVariable Long id, @RequestBody Comment comment) {
		Assert.notNull(id, "评论ID呢？");
		Assert.notEmpty(comment.getContent(), "请输入评论内容");
		Assert.notNull(comment, "这个评论找不到了Q");
		Assert.isTrue(comment.getUserId().equals(SecurityUtils.getUser().getId()), "你不能删除别人的评论哦");

		commentService.update(comment);

		return R.ok(comment);
	}

	// 删除评论
	@DeleteMapping("/delete/{id}")
	public R delete(@PathVariable Long id) {
		Comment comment = commentService.selectById(id);
		Assert.notNull(comment, "这个评论找不到了Q");
		Assert.isTrue(comment.getUserId().equals(SecurityUtils.getUser().getId()), "你不能删除别人的评论哦");
		commentService.delete(comment);
		return R.ok();
	}

	// 点赞评论
	@GetMapping("/{id}/vote")
	public R vote(@PathVariable Long id) {
		Long userId = SecurityUtils.getUser().getId();
		Comment comment = commentService.selectById(id);
		Assert.notNull(comment, "这个评论可能已经被删除了");
		Assert.isTrue(!comment.getUserId().equals(userId), "给自己评论点赞，脸皮真厚！！");
		int voteCount = commentService.vote(comment, userId);
		return R.ok(voteCount);
	}


    //----------------------------------- ADMIN --------------------------------------

    /**
     * 分页查询
     * @param page 分页对象
     * @param comment 评论表
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('community_comment_get')" )
    public R getCommentPage(Page page, Comment comment) {
        return R.ok(commentService.page(page, Wrappers.query(comment)));
    }


    /**
     * 通过id查询评论表
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询评论", description = "通过id查询评论")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('community_comment_get')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(commentService.getById(id));
    }

    /**
     * 新增评论表
     * @param comment 评论表
     * @return R
     */
    @Operation(summary = "新增评论", description = "新增评论")
    @SysLog("新增评论" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('community_comment_add')" )
    public R save(@RequestBody Comment comment) {
        return R.ok(commentService.save(comment));
    }

    /**
     * 修改评论表
     * @param comment 评论表
     * @return R
     */
    @Operation(summary = "修改评论", description = "修改评论")
    @SysLog("修改评论" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('community_comment_edit')" )
    public R updateById(@RequestBody Comment comment) {
        return R.ok(commentService.updateById(comment));
    }

    /**
     * 通过id删除评论表
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id删除评论", description = "通过id删除评论")
    @SysLog("通过id删除评论" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('community_comment_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(commentService.removeById(id));
    }

}
