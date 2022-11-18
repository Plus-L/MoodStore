package com.kci.moodstore.auth.api.holder;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson2.JSONObject;
import com.kci.moodstore.auth.api.bo.UserInfoInTokenBO;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: moodstore
 * @description: 从请求头中获取登录用户信息
 * @author: PlusL
 * @create: 2022-11-18 18:24
 **/
@Component
public class LoginUserHolder {

    public UserInfoInTokenBO getCurrentUser(){
        //从Header中获取用户信息
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String userStr = request.getHeader("user");
        JSONObject userJsonObject = new JSONObject(Integer.parseInt(userStr));
        UserInfoInTokenBO user = new UserInfoInTokenBO();
        user.setUserName(userJsonObject.getString("user_name"));
        user.setId(Convert.toLong(userJsonObject.get("id")));
        user.setRoles(Convert.toList(String.class,userJsonObject.get("authorities")));
        return user;
    }

}
