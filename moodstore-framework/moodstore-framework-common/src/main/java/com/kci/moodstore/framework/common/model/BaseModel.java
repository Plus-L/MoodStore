package com.kci.moodstore.framework.common.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: moodstore-appointment
 * @description: 基础 Model，阿里开发手册规定：表必须含有三个字段:id, create_time, update_time
 * @author: PlusL
 * @create: 2022-10-15 16:54
 **/
@Getter
@Setter
@ToString
public class BaseModel implements Serializable {

    /**
     *创建时间
     */
    protected Date createTime;

    /**
     * 更新时间
     */
    protected Date updateTime;

}
