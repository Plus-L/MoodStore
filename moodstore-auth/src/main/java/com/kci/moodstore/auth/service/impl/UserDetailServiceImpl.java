package com.kci.moodstore.auth.service.impl;

import com.kci.moodstore.auth.model.AuthAccount;
import com.kci.moodstore.auth.model.SecurityUserDetail;
import com.kci.moodstore.auth.service.AuthAccountService;
import com.kci.moodstore.framework.common.result.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: moodstore
 * @description: 用户详细信息，用于SpringSecurity
 * @author: PlusL
 * @create: 2022-11-17 10:45
 **/
@Service
public class UserDetailServiceImpl implements UserDetailsService {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthAccountService authAccountService;

    @Autowired
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String clientId = request.getParameter("client_id");
        AuthAccount authAccount = authAccountService.getAuthAccountByUserName(username).getData();

        if (authAccount==null) {
            throw new UsernameNotFoundException(ResultStatus.USER_NOT_EXIST.getMessage());
        }
        authAccount.setClientId(clientId);
        SecurityUserDetail securityUser = new SecurityUserDetail(authAccount);

        if (!securityUser.isEnabled()) {
            throw new DisabledException(ResultStatus.USER_NOT_EXIST.getMessage());
        } else if (!securityUser.isAccountNonLocked()) {
            throw new LockedException(ResultStatus.USER_NOT_EXIST.getMessage());
        } else if (!securityUser.isAccountNonExpired()) {
            throw new AccountExpiredException(ResultStatus.USER_NOT_EXIST.getMessage());
        } else if (!securityUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(ResultStatus.USER_NOT_EXIST.getMessage());
        }
        return securityUser;
    }

}
