package com.kci.moodstore.framework.common.util;

import org.apache.skywalking.apm.toolkit.trace.TraceContext;

/**
 * @program: moodstore
 * @description: 链路追踪，获取IP
 * @author: PlusL
 * @create: 2022-10-16 16:35
 **/
public class TracerUtils {

    /**
     * 私有化构造方法
     */
    private TracerUtils() {
    }

    /**
     * 获得链路追踪编号，直接返回 SkyWalking 的 TraceId。
     * 如果不存在的话为空字符串！！！
     *
     * @return 链路追踪编号
     */
    public static String getTraceId() {
        return TraceContext.traceId();
    }

}
