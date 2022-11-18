package com.kci.moodstore.auth.mapper;

import com.kci.moodstore.auth.model.AuthAccount;
import com.kci.moodstore.auth.bo.AuthAccountInVerifyBO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @program: moodstore
 * @description: 账号认证Mapper
 * @author: PlusL
 * @create: 2022-11-10 21:53
 **/
@Mapper
public interface AuthAccountMapper {

    /**
     * 根据输入的用户名及用户名类型获取用户信息
     * TODO: 2.mobile的登录模式还未处理，因为这里只有mobile是Integer类型，考虑单独抽出一个方法
     *
     * @param inputUserNameType 输入的用户名类型 1.username 2.mobile 3.email
     * @param inputUserName     输入的用户名
     * @return 用户在token中信息 + 数据库中的密码
     */
    AuthAccountInVerifyBO getAuthAccountInVerifyByInputUserName(@Param("inputUserNameType") Integer inputUserNameType,
                                                                @Param("inputUserName") String inputUserName);

    /**
     * 根据用户id 和系统类型获取平台唯一用户
     *
     * @param id  用户id
     * @param type 系统类型
     * @return 平台唯一用户
     */
    AuthAccount getByUserIdAndType(@Param("id") Long id, @Param("type") Integer type);

    /**
     * 根据用户名获取部分用户信息
     * @param userName 用户名
     * @return 平台唯一用户
     */
    AuthAccount getAuthAccountByUserName(String userName);

}
