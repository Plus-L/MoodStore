package com.kci.moodstore.user;

import com.kci.moodstore.user.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * @program: moodstore-appointment
 * @description:
 * @author: PlusL
 * @create: 2022-10-15 20:45
 **/

@SpringBootTest
@ContextConfiguration(classes = UserApplication.class)
public class UserTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void CrudTest() {
        System.out.println(userMapper.getUserById(1L));
        System.out.println(userMapper.list());
    }

}
