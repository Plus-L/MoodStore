package com.kci.moodstore.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.kci.moodstore.auth.api.bo.UserInfoInTokenBO;
import com.kci.moodstore.auth.mapper.AuthAccountMapper;
import com.kci.moodstore.framework.common.dto.AuthAccount;
import com.kci.moodstore.auth.service.AuthAccountService;
import com.kci.moodstore.framework.common.result.CommonResult;
import com.kci.moodstore.framework.database.util.PrincipalUtil;
import com.kci.moodstore.auth.bo.AuthAccountInVerifyBO;
import com.kci.moodstore.auth.constant.InputUserNameEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.kci.moodstore.framework.common.result.ResultStatus.*;

/**
 * @program: moodstore
 * @description: 账号认证服务实现类
 * @author: PlusL
 * @create: 2022-11-10 21:52
 **/
@Slf4j
@Service
public class AuthAccountServiceImpl implements AuthAccountService {

    @Autowired
    private AuthAccountMapper authAccountMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public CommonResult<UserInfoInTokenBO> getUserInfoInTokenByInputUserNameAndPassword(String inputUserName, String password) {

        if (StrUtil.isBlank(inputUserName)) {
            return CommonResult.error(USERNAME_EMPTY);
        }

        if (StrUtil.isBlank(password)) {
            return CommonResult.error(PASSWORD_EMPTY);
        }

        InputUserNameEnum inputUserNameEnum = null;

        // 用户名
        if (PrincipalUtil.isUserName(inputUserName)) {
            inputUserNameEnum = InputUserNameEnum.USERNAME;
        }

        // TODO: 手机号暂未处理

        // 邮箱
        if (PrincipalUtil.isEmail(inputUserName)) {
            inputUserNameEnum = InputUserNameEnum.EMAIL;
        }

        if (inputUserNameEnum == null) {
            return CommonResult.error(USERNAME_ERROR);
        }

        AuthAccountInVerifyBO authAccountInVerifyBO = authAccountMapper
                .getAuthAccountInVerifyByInputUserName(inputUserNameEnum.value(), inputUserName);

        if (!passwordEncoder.matches(password, authAccountInVerifyBO.getPassword())) {
            return CommonResult.error(NICKNAME_OR_PASSWORD_ERROR);
        }

        return CommonResult.success(authAccountInVerifyBO);
    }

    @Override
    public AuthAccount getByUserIdAndType(Long userId, Integer type) {
        // TODO:考虑是否使用缓存处理
        return authAccountMapper.getByUserIdAndType(userId, type);
    }

    @Override
    public CommonResult<AuthAccount> getAuthAccountByUserName(String userName) {
        // TODO: 同上，考虑是否使用缓存处理
        try{
            AuthAccount authAccount = authAccountMapper.getAuthAccountByUserName(userName);
            if (BeanUtil.isEmpty(authAccount)) {
                return CommonResult.error(USERNAME_ERROR);
            }
            authAccount.setRoles(CollUtil.toList("前台用户"));
            return CommonResult.success(authAccount);
        } catch (Exception e) {
            log.warn("方法 [getAuthAccountByUserName] 异常, 异常信息: " + e);
            return CommonResult.error(USERNAME_ERROR);
        }
    }

}
