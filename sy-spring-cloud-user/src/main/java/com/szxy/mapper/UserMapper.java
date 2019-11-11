package com.szxy.mapper;

import com.szxy.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {


    /**
     * 根据用户手机号查询用户信息
     * @param phone 用户手机号
     * @return
     */
    public User selectByUserPhoneMapper(@Param("phone") String phone);

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
    @Insert("insert into user(username,phone,password,create_at)  values(#{username},#{phone},#{password},sysdate())")
    int inserUserMapper(@Param("username") String username,
                        @Param("phone")String phone,
                        @Param("password")String password);

    /**
     * 更新用户信息
     * @param user
     */
    void updateUserInfoMapper(User user);

    /**
     * 分页查询用户信息
     * @param start  起始位置
     * @param end    结束位置
     * @return
     */
    List<User> selUserByPaginationMapper(@Param("start") Integer start, @Param("end") Integer end);

    @Select("select count(*) from user")
    Integer selCount();

    @Insert("insert into user(phone,username,password) values(#{phone},#{username},#{password})")
    void addUserMapper(User user);
}
