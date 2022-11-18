package com.kci.moodstore.auth.service.impl;

import com.kci.moodstore.auth.model.AuthAccount;
import com.kci.moodstore.auth.model.SecurityUserDetail;
import com.kci.moodstore.auth.service.AuthAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @program: moodstore
 * @description: 用户详细信息，用于SpringSecurity
 * @author: PlusL
 * @create: 2022-11-17 10:45
 **/
public class UserDetailServiceImpl implements UserDetailsService {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthAccountService authAccountService;

    @Override
    public UserDetails loadUserByUsername(String inputUserName) throws UsernameNotFoundException {

        AuthAccount authAccount = authAccountService.getAuthAccountByUserName(inputUserName);
        SecurityUserDetail securityUserDetail = new SecurityUserDetail();
//        securityUserDetail
        return null;
    }

}
