package com.szxy.mapper;


import com.szxy.pojo.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {

    /**
     * 根据物品类别查询物品信息，并按发布时间排序显示前 xxx 件物品
     * @param cateid
     * @param limit
     * @return
     */
    public List<Goods> selByCatelogGoodsId(@Param("cateid") Integer cateid, @Param("limit") Integer limit);

    /**
     * 根据物品 ID 查询物品信息
     * @param id
     * @return
     */
    public Goods selByGoodId(@Param("id")Integer id);

}
