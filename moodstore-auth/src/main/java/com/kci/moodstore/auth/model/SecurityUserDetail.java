package com.kci.moodstore.auth.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @program: moodstore
 * @description:
 * @author: PlusL
 * @create: 2022-11-17 21:58
 **/
@Data
public class SecurityUserDetail implements UserDetails {
    /**
     * ID
     */
    private Long id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户状态
     */
    private Boolean enabled;

    /**
     * 登录客户端ID
     */
    private String clientId;

    /**
     * 权限数据
     */
    private Collection<SimpleGrantedAuthority> authorities;

    public SecurityUserDetail() {

    }

    public SecurityUserDetail(AuthAccount authAccount) {
        this.setId(authAccount.getId());
        this.setUserName(authAccount.getUserName());
        this.setPassword(authAccount.getPassword());
        this.setEnabled(authAccount.getStatus() == 1);
        if (authAccount.getType() != null) {
            authorities = new ArrayList<>();
            authAccount.getRoles().forEach(item -> authorities.add(new SimpleGrantedAuthority(item)));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
