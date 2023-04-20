package com.kci.moodstore.community.entity.dto;

import com.kci.moodstore.community.entity.Comment;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @program: moodstore
 * @description: 话题下面的评论列表单个对象的数据结构
 * @author: PlusL
 * @create: 2023-04-18 19:59
 **/
@Getter
@Setter
public class CommentsByTopic extends Comment implements Serializable {

	private String username;
	private String avatar;
	// 评论的层级，直接评论话题的，layer即为0，如果回复了评论的，则当前回复的layer为评论对象的layer+1
	private Integer layer;

	private LinkedHashMap<Integer, List<CommentsByTopic>> children;

}
