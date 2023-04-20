package com.kci.moodstore.community;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kci.moodstore.community.mapper.TopicMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * @program: moodstore
 * @description:
 * @author: PlusL
 * @create: 2023-04-18 14:42
 **/
@SpringBootTest
public class CommunityMapperTest {

	@Autowired
	private TopicMapper topicMapper;

	@Test
	public void mapperTest() {
		Page page = new Page();
		System.out.println(JSON.toJSONString(topicMapper.search(page, "图书馆")));
	}

}
