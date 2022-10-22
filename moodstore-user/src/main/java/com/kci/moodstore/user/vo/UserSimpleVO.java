package com.kci.moodstore.user.vo;

import com.kci.moodstore.framework.common.vo.BaseVO;

/**
 * @program: moodstore
 * @description: 展示层user简易对象
 * @author: PlusL
 * @create: 2022-10-19 18:10
 **/
public class UserSimpleVO extends BaseVO {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户头像
     */
    private String headerUrl;

    /**
     * 用户状态：1 正常 | 0 无效
     */
    private int status;

}
