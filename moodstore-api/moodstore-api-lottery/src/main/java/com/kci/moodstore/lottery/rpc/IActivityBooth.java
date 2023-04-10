package com.kci.moodstore.lottery.rpc;

import com.kci.moodstore.lottery.rpc.req.ActivityReq;
import com.kci.moodstore.lottery.rpc.res.ActivityRes;

/**
 * @program: moodstore-lottery
 * @description: 活动展台 - 接口  (前置的字母I表示Interface)
 * @author: PlusL
 * @create: 2023-03-24 16:33
 **/
public interface IActivityBooth {

    ActivityRes queryActivityById(ActivityReq req);

}
