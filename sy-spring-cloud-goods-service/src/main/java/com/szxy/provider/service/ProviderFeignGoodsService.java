package com.szxy.provider.service;

import com.szxy.pojo.Goods;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sun.misc.Request;

import java.util.List;

/**
 * 物品服务 ——服务提供者接口
 */
@RequestMapping("/goods")
public interface ProviderFeignGoodsService {

    /**
     * 根据物品属于类别 查看按发布时间排序
     * 前 xxx 个物品信息
     * @param cateid
     * @return
     */
    @RequestMapping(value = "/catelogGoods/{cateid}/{limit}", method = RequestMethod.GET)
    public List<Goods> findByCatelogGoodsIdService(@RequestParam("cateid") Integer  cateid, @RequestParam("limit") Integer limit);

    /**
     * 查询单个物品信息
     * @param id
     * @return
     */
    @RequestMapping(value="/good/{id}",method = RequestMethod.GET)
    public Goods findByGoodIdService(@PathVariable Integer id);


}
