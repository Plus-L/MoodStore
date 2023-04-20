package com.kci.moodstore.community.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kci.moodstore.common.core.util.R;
import com.kci.moodstore.community.entity.MyTag;
import com.kci.moodstore.community.service.TagService;
import com.kci.moodstore.community.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @program: moodstore
 * @description: 首页Controller
 * @author: PlusL
 * @create: 2023-04-19 17:02
 **/
@RestController
@RequiredArgsConstructor
public class IndexController {

	private final TopicService topicService;

	private final TagService tagService;



	// 根据标签名返回标签下话题
	@GetMapping("/tag/{name}")
	public R topicsByTagName(Page page, @PathVariable String name) {
		MyTag tag = tagService.selectByName(name);
		if (tag == null) {
			return R.failed("标签不存在");
		} else {
			IPage<Map<String, Object>> iPage = tagService.selectTopicByMyTagId(tag.getId(), page);
			return R.ok(iPage);
		}
	}

}
