package com.szxy.mapper;

import com.szxy.pojo.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select * from user where phone = #{0}")
    public User selectByUserPhone(String phone);

}
