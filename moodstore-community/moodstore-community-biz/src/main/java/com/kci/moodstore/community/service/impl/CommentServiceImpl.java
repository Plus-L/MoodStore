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
package com.kci.moodstore.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kci.moodstore.community.entity.Comment;
import com.kci.moodstore.community.entity.Topic;
import com.kci.moodstore.community.entity.dto.CommentsByTopic;
import com.kci.moodstore.community.mapper.CommentMapper;
import com.kci.moodstore.community.mapper.TopicMapper;
import com.kci.moodstore.community.service.CommentService;
import com.kci.moodstore.community.service.NotificationService;
import com.kci.moodstore.community.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;



import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 评论表
 *
 * @author PlusL
 * @date 2023-04-16 16:02:55
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

	private final TopicMapper topicMapper;

	private final NotificationService notificationService;

	@Override
	public List<CommentsByTopic> selectByTopicId(Long topicId) {
		List<CommentsByTopic> commentsByTopics = baseMapper.selectByTopicId(topicId);
		// 对评论内容进行过滤，然后再写入redis
		//TODO: 敏感词过滤待完成
/*		for (CommentsByTopic commentsByTopic : commentsByTopics) {
			commentsByTopic.setContent(SensitiveWordUtil.replaceSensitiveWord(commentsByTopic.getContent(), "*",
					SensitiveWordUtil.MinMatchType));
		}*/
		return commentsByTopics;
	}

	@Override
	public void deleteByTopicId(Long topicId) {
		QueryWrapper<Comment> wrapper = new QueryWrapper<>();
		wrapper.lambda().eq(Comment::getTopicId, topicId);
		baseMapper.delete(wrapper);
	}

	@Override
	public void deleteByUserId(Long userId) {
		QueryWrapper<Comment> wrapper = new QueryWrapper<>();
		wrapper.lambda().eq(Comment::getUserId, userId);
		baseMapper.delete(wrapper);
	}

	@Override
	public Comment insert(Comment comment) {

		//TODO: 保留审核功能，暂不处理
		comment.setStatus(true);// 无需审核
		baseMapper.insert(comment);

		// 话题的评论数+1
		Topic topic = topicMapper.selectById(comment.getTopicId());
		topic.setCommentCount(topic.getCommentCount() + 1);
		topicMapper.update(topic, null);

		Long userId = comment.getUserId();

		// TODO: 处理敏感词

		// 通知
		// 给评论的作者发通知
		if (comment.getCommentId() != null) {
			Comment targetComment = this.selectById(comment.getCommentId());
			if (!userId.equals(targetComment.getUserId())) {
				notificationService.insert(userId, targetComment.getUserId(), topic.getId(), "REPLY", comment
						.getContent());

				String emailTitle = "你在话题 %s 下的评论被 %s 回复了，快去看看吧！";
				//TODO: 后续计划处理WebSocket和邮件发送通知
				// 如果开启了websocket，就发网页通知
/*				if (systemConfigService.selectAllConfig().get("websocket").equals("1")) {
					MyWebSocket.emit(targetComment.getUserId(), new Message("notifications", String.format(emailTitle, topic
							.getTitle(), user.getUsername())));
					MyWebSocket.emit(targetComment.getUserId(), new Message("notification_notread", 1));
				}*/
				// 发送邮件通知
/*				UserVO targetUser = userService.getUserVoById(targetComment.getUserId());
				if (!StringUtils.isEmpty(targetUser.getEmail()) && targetUser.getEmailNotification()) {
					String emailContent = "回复内容: %s <br><a href='%s/topic/%s' target='_blank'>传送门</a>";
					new Thread(() -> emailService.sendEmail(targetUser.getEmail(), String.format(emailTitle, topic.getTitle(),
							user.getUsername()), String.format(emailContent, comment.getContent(), systemConfigService
							.selectAllConfig().get("base_url"), topic.getId()))).start();
				}*/
			}
		}
		// 给话题作者发通知
		if (!userId.equals(topic.getUserId())) {
			notificationService.insert(userId, topic.getUserId(), topic.getId(), "COMMENT", comment.getContent());
			// 发送邮件通知
			String emailTitle = "%s 评论你的话题 %s 快去看看吧！";
			// 如果开启了websocket，就发网页通知
/*			if (systemConfigService.selectAllConfig().get("websocket").equals("1")) {
				MyWebSocket.emit(topic.getUserId(), new Message("notifications", String.format(emailTitle, user.getUsername()
						, topic.getTitle())));
				MyWebSocket.emit(topic.getUserId(), new Message("notification_notread", 1));
			}*/
			// 邮箱通知
/*			User targetUser = userService.selectById(topic.getUserId());
			if (!StringUtils.isEmpty(targetUser.getEmail()) && targetUser.getEmailNotification()) {
				String emailContent = "评论内容: %s <br><a href='%s/topic/%s' target='_blank'>传送门</a>";
				new Thread(() -> emailService.sendEmail(targetUser.getEmail(), String.format(emailTitle, user.getUsername(),
						topic.getTitle()), String.format(emailContent, comment.getContent(), systemConfigService.selectAllConfig
						().get("base_url"), topic.getId()))).start();
			}*/
		}

		// TODO: 发送Telegram通知
/*		new Thread(() -> {
			String formatMessage;
			String domain = systemConfigService.selectAllConfig().get("base_url");
			if (systemConfigService.selectAllConfig().get("content_style").equals("MD")) {
				formatMessage = String.format("%s 评论了话题 [%s](%s) 内容： %s", user.getUsername(), topic.getTitle(), domain + "/topic/" + topic.getId(), StringUtil.removeSpecialChar(comment.getContent()));
			} else {
				formatMessage = String.format("%s 评论了话题 <a href=\"%s\">%s</a> 内容： %s", user.getUsername(), domain + "/topic/" + topic.getId(), topic.getTitle(), StringUtil.removeSpecialChar(comment.getContent()));
			}
			Integer message_id = telegramBotService.init().sendMessage(formatMessage, true, null);
			Comment newComment = new Comment();
			newComment.setId(comment.getId());
			newComment.setTgMessageId(message_id);
			commentMapper.updateById(newComment);

		}).start();*/

		return comment;
	}

	@Override
	public Comment selectById(Long id) {
		return baseMapper.selectById(id);
	}

	@Override
	public Comment selectByTgMessageId(Long messageId) {
		QueryWrapper<Comment> wrapper = new QueryWrapper<>();
		wrapper.lambda().eq(Comment::getTgMessageId, messageId);
		List<Comment> comments = baseMapper.selectList(wrapper);
		return comments.size() > 0 ? comments.get(0) : null;
	}

	@Override
	public void update(Comment comment) {
		//TODO : 过滤敏感词
		baseMapper.updateById(comment);
	}

	@Override
	public int vote(Comment comment, Long userId) {
		String upIds = comment.getUpIds();
		// 将点赞用户id的字符串转成集合
		Set<String> strings = StringUtils.commaDelimitedListToSet(upIds);
		// 把新的点赞用户id添加进集合，这里用set，正好可以去重，如果集合里已经有用户的id了，那么这次动作被视为取消点赞
		//TODO: 增加用户积分

		if (strings.contains(String.valueOf(userId))) { // 取消点赞行为
			strings.remove(String.valueOf(userId));
		} else { // 点赞行为
			strings.add(String.valueOf(userId));
		}
		// 再把这些id按逗号隔开组成字符串
		comment.setUpIds(StringUtils.collectionToCommaDelimitedString(strings));
		// 更新评论
		this.update(comment);

		return strings.size();
	}

	@Override
	public void delete(Comment comment) {
		if (comment != null) {
			// 话题评论数-1
			Topic topic = topicMapper.selectById(comment.getTopicId());
			topic.setCommentCount(topic.getCommentCount() - 1);
			topicMapper.update(topic, null);
			//TODO: 减去用户积分

			// 删除评论
			baseMapper.deleteById(comment.getId());
		}
	}

	@Override
	public IPage<Map<String, Object>> selectByUserId(Long userId, Page page) {
		// TODO: 敏感词过滤
		return baseMapper.selectByUserId(page, userId);
	}


	@Override
	public int countToday() {
		return baseMapper.countToday();
	}
}
