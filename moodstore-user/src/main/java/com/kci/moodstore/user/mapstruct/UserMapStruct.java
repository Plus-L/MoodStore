package com.kci.moodstore.user.mapstruct;

import com.kci.moodstore.user.dto.UserDTO;
import com.kci.moodstore.user.model.User;
import com.kci.moodstore.user.vo.UserSimpleVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @program: moodstore
 * @description: 用户类实体转换工具
 * @author: PlusL
 * @create: 2022-10-19 19:25
 **/
@Mapper
public interface UserMapStruct {

    UserMapStruct INSTANCE = Mappers.getMapper(UserMapStruct.class);

    UserDTO toDTO(User user);

    UserSimpleVO toSimpleVO(User user);

    List<UserSimpleVO> toSimpleVOList(List<User> list);

}
