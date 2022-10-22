package com.kci.moodstore.framework.security.adapter;

import java.util.List;

/**
 * @program: moodstore
 * @description: 认证配置适配器,实现该接口之后,修改需要授权登陆的路径,不需要授权登陆的路径
 * @author: PlusL
 * @create: 2022-10-20 22:02
 **/
public interface AuthConfigAdapter {

    /**
     * 需要授权登陆的路径
     * @return 需要授权登陆的路径列表
     */
    List<String> pathPatterns();

    /**
     * 不需要授权登陆的路径
     * @param paths
     * @return 不需要授权登陆的路径列表
     */
    List<String> excludePathPatterns(String... paths);


}
