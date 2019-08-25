package com.szxy.mapper;

import com.szxy.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    /**
     * 根据用户手机号查询用户信息
     * @param phone 用户手机号
     * @return
     */
    @Select("select * from user where phone = #{0}")
    public User selectByUserPhoneMapper(String phone);

    /**
     * 根据用户 ID 查询卖家信息
     * @param id
     * @return
     */
    User selSellerInfoByIdMapper(@Param("id" )Integer id);

    /**
     * 添加用户信息
     * @param username 用户名
     * @param phone    手机号
     * @param password  密码
     * @return
     */
    @Insert("insert into user(phone,username,password)  values(#{username},#{phone},#{password})")
    int inserUserMapper(@Param("username") String username,
                        @Param("phone")String phone,
                        @Param("password")String password);
}
