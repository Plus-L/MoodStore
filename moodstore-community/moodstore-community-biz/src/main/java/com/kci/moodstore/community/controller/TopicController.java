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
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kci.moodstore.common.core.util.R;
import com.kci.moodstore.common.log.annotation.SysLog;
import com.kci.moodstore.common.security.service.MoodstoreUser;
import com.kci.moodstore.common.security.util.SecurityUtils;
import com.kci.moodstore.community.entity.Collect;
import com.kci.moodstore.community.entity.MyTag;
import com.kci.moodstore.community.entity.Topic;
import com.kci.moodstore.community.entity.dto.CommentsByTopic;
import com.kci.moodstore.community.service.CollectService;
import com.kci.moodstore.community.service.CommentService;
import com.kci.moodstore.community.service.TagService;
import com.kci.moodstore.community.service.TopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 话题表
 *
 * @author PlusL
 * @date 2023-04-16 16:02:55
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/topic" )
@Tag(name = "话题表管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class TopicController {

    private final TopicService topicService;

    private final TagService tagService;

    private final CommentService commentService;

    private final CollectService collectService;

//    private final SysUserService userService;

	// 获取话题列表
	@GetMapping({"/", "/index"})
	public R<IPage<Map<String, Object>>> index(Page page, @RequestParam(defaultValue = "all") String
			tab) {
		IPage<Map<String, Object>> iPage = topicService.selectAll(page, tab);
		return R.ok(iPage);
	}

	// 话题详情
	@GetMapping("/detail/{id}")
	public R detail(@PathVariable Long id) {
		Map<String, Object> map = new HashMap<>();
		// 查询话题详情
		Topic topic = topicService.getById(id);
		// 查询话题关联的标签
		List<MyTag> tags = tagService.selectByTopicId(id);
		// 查询话题的评论
		List<CommentsByTopic> comments = commentService.selectByTopicId(id);
		// 查询话题的作者信息
//		UserVO topicUser = userService.getUserVoById(topic.getUserId());
		// 查询话题有多少收藏
		List<Collect> collects = collectService.selectByTopicId(id);
		// 如果自己登录了，查询自己是否收藏过这个话题
		MoodstoreUser user = SecurityUtils.getUser();
		if (user != null) {
			Collect collect = collectService.selectByTopicIdAndUserId(id, user.getId());
			map.put("collect", collect);
		}
		// 话题浏览量+1
		// TODO: IP暂未处理，后续加入SkyWalking做IP监控
		String ip = "127.0.0.1";
		ip = ip.replace(":", "_").replace(".", "_");
		topic = topicService.updateViewCount(topic, ip);
//		topic.setContent(SensitiveWordUtil.replaceSensitiveWord(topic.getContent(), "*", SensitiveWordUtil.MinMatchType));

		map.put("topic", topic);
		map.put("tags", tags);
		map.put("comments", comments);
//		map.put("topicUser", topicUser);
		map.put("collects", collects);
		return R.ok(map);
	}

	// 保存话题
/*	@PostMapping
	public R create(Topic topic) {

		topicService.insert(topic);
		topic.setContent(SensitiveWordUtil.replaceSensitiveWord(topic.getContent(), "*", SensitiveWordUtil.MinMatchType));
		return R.ok(topic);
	}*/

	// 更新话题
	@PutMapping(value = "/update/{id}")
	public R edit(@PathVariable Long id, @RequestBody Topic topic) {
		Assert.notEmpty(topic.getContent(), "修改内容不能为空");
		// 更新话题
		Assert.isTrue(topic.getUserId().equals(SecurityUtils.getUser().getId()), "你不能修改别人的话题");
		// 更新修改时间
		topic.setModifyTime(LocalDateTime.now());
		topicService.updateTopic(topic, null);
		return R.ok(topic);
	}

	// 删除话题
	@DeleteMapping("/delete/{id}")
	public R delete(@PathVariable Long id) {
		Topic topic = topicService.getById(id);
		Assert.isTrue(topic.getUserId().equals(SecurityUtils.getUser().getId()), "你不能修改别人的话题");
		topicService.delete(topic);
		return R.ok();
	}

	@GetMapping("/{id}/vote")
	public R vote(@PathVariable Long id) {
		Topic topic = topicService.getById(id);
		Assert.notNull(topic, "这个话题可能已经被删除了");
		int voteCount = topicService.vote(topic, SecurityUtils.getUser().getId());
		return R.ok(voteCount);
	}


    // ------------------------------------- ADMIN ------------------------------------------

    /**
     * 分页查询
     * @param page 分页对象
     * @param topic 话题表
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('community_topic_get')" )
    public R getTopicPage(Page page, Topic topic) {
        return R.ok(topicService.page(page, Wrappers.query(topic)));
    }


    /**
     * 通过id查询话题表
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('community_topic_get')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(topicService.getById(id));
    }

    /**
     * 新增话题表
     * @param topic 话题表
     * @return R
     */
    @Operation(summary = "新增话题表", description = "新增话题表")
    @SysLog("新增话题表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('community_topic_add')" )
    public R save(@RequestBody Topic topic) {
        return R.ok(topicService.save(topic));
    }

    /**
     * 修改话题表
     * @param topic 话题表
     * @return R
     */
    @Operation(summary = "修改话题表", description = "修改话题表")
    @SysLog("修改话题表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('community_topic_edit')" )
    public R updateById(@RequestBody Topic topic) {
        return R.ok(topicService.updateById(topic));
    }

    /**
     * 通过id删除话题表
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id删除话题表", description = "通过id删除话题表")
    @SysLog("通过id删除话题表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('community_topic_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(topicService.removeById(id));
    }

}
