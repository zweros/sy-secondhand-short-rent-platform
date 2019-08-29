package com.szxy.mapper;

import com.szxy.pojo.Image;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface GoodsImageMapper {

    @Select(" select " +
            " id,goods_id as goodsId,img_url as imgUrl " +
            " from image " +
            " where goods_id = #{goodId}")
    public Image selGoodsImageByGoodId(@Param("goodId") Integer goodId);

    @Insert("insert into image values(default,#{goodsId},#{imgUrl})")
    void insertImageMapper(Image image);
}
