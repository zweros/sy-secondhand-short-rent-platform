package com.szxy.provider.service;

import com.szxy.pojo.Goods;
import com.szxy.utils.GoodsGrid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 物品服务 ——服务提供者接口
 */
//@RequestMapping("/goods")
public interface ProviderFeignGoodsService {

    /**
     * 根据物品属于类别 查看按发布时间排序
     * 前 xxx 个物品信息
     * @param cateid  物品目录类别
     * @return
     */
    @RequestMapping(value="/catelogGoods", method = RequestMethod.GET)
    public List<Goods> findByCatelogGoodsIdService(@RequestParam(value="cateid",required = false) Integer  cateid, @RequestParam(value="limit",required = false) Integer limit);

    /**
     * 查询单个物品信息
     * @param id
     * @return
     */
    @RequestMapping(value="/good",method = RequestMethod.GET)
    public Goods findByGoodIdService(@RequestParam(value="id") Integer id);

    /**
     * 添加物品
     * @param goods
     */
    @RequestMapping(value="/addGoods",method = RequestMethod.POST)
    public void addGoodsService(@RequestBody Goods goods);

    /**
     * 搜索物品
     * @param str
     * @return
     */
    @RequestMapping(value="/searchGoods",method = RequestMethod.GET)
    List<Goods> searchGoodsService(@RequestParam("str") String str);

    /**
     * 查找用户已发布的物品
     * @param userId
     * @return
     */
    @RequestMapping(value="/findUserPublishedAllGoods",method = RequestMethod.GET)
    List<Goods>  findUserPublishedAllGoodsService(@RequestParam("userId") Integer userId);

    /**
     * 更新物品信息
     * @param goods
     */
    @RequestMapping(value="/updateGood",method = RequestMethod.POST)
    void updateGoodsService(@RequestBody  Goods goods);

    /**
     * 删除物品信息
     * @param goodId 物品 ID
     */
    @RequestMapping(value="/delGood",method = RequestMethod.GET)
    void deleteGoodsService(@RequestParam("goodId") Integer goodId);

    /**
     * 物品条件查询
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value="/findGoodByPagination",method = RequestMethod.GET)
    GoodsGrid findGoodsByPagination(@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize);
}
