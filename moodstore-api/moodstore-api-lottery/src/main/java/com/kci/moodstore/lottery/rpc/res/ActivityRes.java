package com.kci.moodstore.lottery.rpc.res;

import com.kci.moodstore.framework.common.result.CommonResult;
import com.kci.moodstore.lottery.rpc.dto.ActivityDto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @program: moodstore-lottery
 * @description: ActivityResponse 活动响应类
 * @author: PlusL
 * @create: 2023-04-01 18:41
 **/
@Getter
@Setter
public class ActivityRes implements Serializable {

    private CommonResult result;

    private ActivityDto activity;

    public ActivityRes() {
    }

    public ActivityRes(CommonResult result, ActivityDto activity) {
        this.result = result;
        this.activity = activity;
    }

    public ActivityRes(CommonResult result) {
        this.result = result;
    }

}
