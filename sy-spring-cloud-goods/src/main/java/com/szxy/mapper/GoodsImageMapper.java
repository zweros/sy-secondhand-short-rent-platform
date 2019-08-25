package com.szxy.mapper;

import com.szxy.pojo.Image;
import org.apache.ibatis.annotations.Param;

public interface GoodsImageMapper {

    public Image selGoodsImageByGoodId(@Param("goodId") Integer  goodId);

}
