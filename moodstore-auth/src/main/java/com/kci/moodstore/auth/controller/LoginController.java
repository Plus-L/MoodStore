package com.kci.moodstore.auth.controller;

import com.kci.moodstore.framework.common.result.CommonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: moodstore
 * @description: 登录测试 Controller
 * @author: PlusL
 * @create: 2022-11-11 21:31
 **/
@RestController
public class LoginController {

    @GetMapping("/api/hello")
    public CommonResult<String> helloTest() {
        return CommonResult.success("hello world! auth model test");
    }


}
