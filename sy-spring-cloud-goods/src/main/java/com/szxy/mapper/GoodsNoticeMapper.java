package com.szxy.mapper;

import com.szxy.pojo.Notice;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Auther:zwer
 * @Date:2019/8/29 16:46
 * @Description:com.szxy.mapper
 * @Version:1.0
 **/
public interface GoodsNoticeMapper {


    List<Notice> selAllNoticeMapper();

    void addNoticeMapper(Notice notice);

    List<Notice> selNoticeByPagination(@Param("startPos") Integer startPos,@Param("endPos") Integer endPos);

    @Select("select count(id) from notice")
    Integer selAllNoticeCount();
}
