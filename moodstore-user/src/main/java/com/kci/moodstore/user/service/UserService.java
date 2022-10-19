package com.kci.moodstore.user.service;


import com.kci.moodstore.user.dto.UserDTO;
import com.kci.moodstore.user.model.User;

/**
 * @program: moodstore-appointment
 * @description:
 * @author: PlusL
 * @create: 2022-10-15 20:59
 **/
public interface UserService {

    UserDTO getUserById(Long id);

}
