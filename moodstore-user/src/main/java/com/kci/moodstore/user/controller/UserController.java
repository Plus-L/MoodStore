package com.kci.moodstore.user.controller;

import com.kci.moodstore.framework.common.result.CommonResult;
import com.kci.moodstore.user.model.User;
import com.kci.moodstore.user.service.UserService;
import com.kci.moodstore.user.vo.UserSimpleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: moodstore
 * @description: 用户控制类
 * @author: PlusL
 * @create: 2022-10-20 21:24
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user_simple_info")
    public CommonResult<UserSimpleVO> getSimpleUserInfoById() {
        // TODO: 待加入认证以及缓存
        return CommonResult.success(new UserSimpleVO());
    }
}
