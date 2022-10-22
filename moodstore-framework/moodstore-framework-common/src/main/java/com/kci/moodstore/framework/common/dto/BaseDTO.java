package com.kci.moodstore.framework.common.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @program: moodstore
 * @description: 基础BaseDTO， 阿里开发手册规定：表必须含有三个字段:id, create_time, update_time
 * @author: PlusL
 * @create: 2022-10-20 21:47
 **/
@Getter
@Setter
@ToString
public class BaseDTO {

    /**
     *创建时间
     */
    protected Date createTime;

    /**
     * 更新时间
     */
    protected Date updateTime;

}
