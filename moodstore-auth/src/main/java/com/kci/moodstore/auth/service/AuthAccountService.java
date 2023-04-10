package com.kci.moodstore.auth.service;

import com.kci.moodstore.auth.api.bo.UserInfoInTokenBO;
import com.kci.moodstore.framework.common.dto.AuthAccount;
import com.kci.moodstore.framework.common.result.CommonResult;

/**
 * @program: moodstore
 * @description: 验证模块 - Service
 * @author: PlusL
 * @create: 2022-11-10 16:52
 **/
public interface AuthAccountService {

    /**
     * 通过输入的用户名密码，校验并获取部分用户信息
     * @param inputUserName 输入的用户名（用户名）
     * @param password 密码
     * @return 用户在token中信息
     */
    CommonResult<UserInfoInTokenBO> getUserInfoInTokenByInputUserNameAndPassword(String inputUserName,
                                                                                 String password);

    /**
     * 根据用户id 和系统类型获取平台唯一用户
     * @param userId 用户id
     * @param type 系统类型
     * @return 平台唯一用户
     */
    AuthAccount getByUserIdAndType(Long userId, Integer type);

    /**
     * 根据用户名获取部分用户信息
     * @param userName 用户名
     * @return 平台唯一用户
     */
    CommonResult<AuthAccount> getAuthAccountByUserName(String userName);

}
