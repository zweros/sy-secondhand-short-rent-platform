package com.szxy.mapper;

import com.szxy.pojo.Focus;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Auther:zwer
 * @Date:2019/8/28 15:28
 * @Description:com.szxy.mapper
 * @Version:1.0
 **/
public interface UserFocusMapper {

    @Insert("insert into focus values(default,#{goodsId},#{userId})")
    void addGoodsFocusMapper(@Param("goodsId") Integer goodId, @Param("userId") Integer uid);

    @Select("select id,goods_id goodsId,user_id userId from focus where user_id = #{userId}")
    List<Focus> selGoodsFocusByUserIdMapper(@Param("userId") Integer userId);

}
