package com.kci.moodstore.auth;

import com.kci.moodstore.auth.mapper.AuthAccountMapper;
import com.kci.moodstore.auth.service.AuthAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;

/**
 * @program: moodstore
 * @description:
 * @author: PlusL
 * @create: 2022-11-11 18:28
 **/
@SpringBootTest
@ContextConfiguration(classes = MoodstoreAuthApplication.class)
public class AuthSimpleTest {

    @Autowired
    private AuthAccountMapper accountMapper;

    @Autowired
    private AuthAccountService authAccountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void simpleMapperTest() {
        System.out.println(accountMapper.getAuthAccountInVerifyByInputUserName(1, "PlusL"));

//        System.out.println(accountMapper.getByUserIdAndType(1L, 1));
    }

    @Test
    public void authServiceTest() {
        System.out.println(authAccountService
                .getUserInfoInTokenByInputUserNameAndPassword("PlusL", "123456"));

        System.out.println(authAccountService.getAuthAccountByUserName("PlusL"));
    }

    @Test
    public void passwordGenerator() {
        System.out.println(passwordEncoder.encode("123456"));
    }

}
