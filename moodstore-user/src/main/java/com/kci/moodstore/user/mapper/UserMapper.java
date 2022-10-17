package com.kci.moodstore.user.mapper;

import com.kci.moodstore.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: moodstore-appointment
 * @description: 用户 Mapper
 * @author: PlusL
 * @create: 2022-10-15 19:32
 **/
@Mapper
public interface UserMapper {

    /**
     * 通过用户id获取用户信息
     * @param id
     * @return
     */
    User getUserById(@Param("id") Long id);

    /**
     * 通过用户名获取用户信息
     * @param username
     * @return
     */
    User getByUserName(@Param("username") String username);

    /**
     * 插入用户
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 更新用户激活状态
     * @param id
     * @param status
     * @return
     */
    int updateStatus(int id, int status);

    /**
     * 获取用户列表
     * @return
     */
    List<User> list();



}
