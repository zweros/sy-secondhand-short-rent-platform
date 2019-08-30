package com.szxy.service;

import com.szxy.pojo.Goods;
import com.szxy.utils.GoodsGrid;

import java.util.List;

public interface ProviderGoodsService {

    /**
     * 根据物品类别查询物品信息，并按发布时间排序显示前 xxx 件物品
     * @param cateid
     * @param limit
     * @return
     */
    public List<Goods> findByCatelogGoodsIdService( Integer cateid, Integer limit);

    /**
     * 根据物品 ID 查询物品信息
     * @param id
     * @return
     */
    public Goods findByGoodIdService(Integer id);

    /**
     * 添加物品
     * @param goods
     */
    void addGoodsService(Goods goods);

    /**
     * 搜索物品
     * @param str
     * @return
     */
    List<Goods> searchGoodsService(String str);

    /**
     * 查找用户已发布的物品
     * @param userId
     * @return
     */
    List<Goods> findUserPublishedAllGoodsService(Integer userId);

    /**
     * 更新物品信息
     * @param goods
     */
    void updateGoodsService(Goods goods);

    /**
     * 删除物品信息
     * @param goodId
     */
    void deleteGoodService(Integer goodId);

    /**
     * 物品分页查询
     * @param page    当前页号
     * @param pageSize  每页的显示记录数
     * @return
     */
    GoodsGrid findGoodsByPaginationService(Integer page, Integer pageSize);
}
