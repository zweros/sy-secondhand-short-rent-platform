package com.szxy.mapper;

import com.szxy.pojo.Catelog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GoodsCatelogMapper {

    @Select("select id,name,number,status from catelog")
    List<Catelog>  selAllCatelogmMapper();

    @Select("select id,name,number,status from catelog where id = #{id}")
    Catelog selCatelogByIdMapper(@Param("id")Integer id);

}
