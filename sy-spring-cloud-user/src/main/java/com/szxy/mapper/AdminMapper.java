package com.szxy.mapper;

import com.szxy.pojo.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Auther:zwer
 * @Date:2019/9/2 20:39
 * @Description:com.szxy.mapper
 * @Version:1.0
 **/
public interface AdminMapper {

    @Insert("insert into admin value(default,#{username},#{phone},#{password},'管理员')")
    void insertAdmin(@Param("username") String username,
                     @Param("phone") String phone,
                     @Param("password") String password);

    @Select("select id,username userName,phone,password,userRole from admin where phone = #{phone}")
    Admin selAdminByPhone(@Param("phone") String phone);

    @Select("select id,username userName,phone,password,userRole from admin where id = #{id}")
    Admin selAdminById(@Param("id") Integer id);

}
