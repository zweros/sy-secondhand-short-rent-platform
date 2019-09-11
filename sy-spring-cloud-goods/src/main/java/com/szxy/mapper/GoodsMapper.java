package com.szxy.mapper;


import com.szxy.pojo.Goods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    /**
     * 添加物品
     * @param goods
     */
    void insertGoodsMapper(Goods goods);

    /**
     * 模糊查询物品
     * @param str
     * @return
     */
    List<Goods> findGoodsByNameMapper(@Param("name")String str);

    /**
     * 查找用户已发布的闲置物品
     * @param userId
     * @return
     */
    List<Goods> selUserPublishedAllGoodsMapper(@Param("userId")Integer userId);

    /**
     * 更新物品信息
     * @param goods
     */
    void updateGoodsMapper(Goods goods);

    /**
     * 删除物品信息
     * @param goodId
     */
    //@Delete("delete from goods where id = #{goodId")
    @Update("update goods set status = 0 where id = #{goodId}")
    void delGoodsMapper(@Param("goodId") Integer goodId);

    /**
     * 物品分页条件查询
     * @param start  查询起始位置
     * @param end    查询结束位置
     * @return
     */
    List<Goods> selGoodsByPaginationMapper(@Param("start") Integer start,@Param("end")Integer end);

    @Select("select count(id) from goods")
    int selGoodsAllCountMapper();
}
