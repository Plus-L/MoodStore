package com.kci.moodstore.user.service;


import com.kci.moodstore.framework.database.dto.PageDTO;
import com.kci.moodstore.framework.database.vo.PageVO;
import com.kci.moodstore.user.dto.UserDTO;
import com.kci.moodstore.user.vo.UserSimpleVO;

import java.util.List;

/**
 * @program: moodstore-appointment
 * @description:
 * @author: PlusL
 * @create: 2022-10-15 20:59
 **/
public interface UserService {

    UserDTO getUserById(Long id);

    PageVO<UserSimpleVO> getUserInPage(PageDTO pageDTO);

    List<UserSimpleVO> getUserByUserIds(List<Long> userIds);

}
