package com.kci.moodstore.lottery.rpc.req;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @program: moodstore-lottery
 * @description: ActivityRequest 活动请求类
 * @author: PlusL
 * @create: 2023-04-01 18:40
 **/
@Getter
@Setter
public class ActivityReq implements Serializable {

    private Long activityId;

}
