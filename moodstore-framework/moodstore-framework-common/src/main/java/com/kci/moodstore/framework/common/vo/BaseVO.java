package com.kci.moodstore.framework.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @program: moodstore
 * @description: 基础 VO，阿里开发手册规定：表必须含有三个字段:id, create_time, update_time
 * @author: PlusL
 * @create: 2022-10-19 18:13
 **/
@Getter
@Setter
@ToString
public class BaseVO {

    /**
     *创建时间
     */
    protected Date createTime;

    /**
     * 更新时间
     */
    protected Date updateTime;

}
