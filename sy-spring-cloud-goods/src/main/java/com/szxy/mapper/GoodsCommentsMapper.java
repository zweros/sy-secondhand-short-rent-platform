package com.szxy.mapper;

import com.szxy.pojo.Comments;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Auther:zwer
 * @Date:2019/8/25 22:23
 * @Description:com.szxy.mapper
 * @Version:1.0
 **/
public interface GoodsCommentsMapper {

    List<Comments> selCommentsByGoodsId(@Param("goodId") Integer goodId);

    List<Comments> selCommentsByUserId(@Param("userId") Integer userId);

    int insertCommentsMapper(Comments comment);
}
