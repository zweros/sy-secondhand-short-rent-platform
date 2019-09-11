package com.szxy.service;


import com.szxy.pojo.Goods;
import com.szxy.utils.GoodsGrid;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * There was an unexpected error (type=Internal Server Error, status=500).
 * status 404 reading GoodsService#findByCatelogGoodsIdService(Integer,Integer); content: {"timestamp":1566974951215,"status":404,"error":"Not Found","message":"No message available",
 * "path":"/catelogGoods"}
 */

/**
 * 物品服务
 *  Feign 注意： Only single inheritance supported: GoodsService
 */
//@FeignClient(name="sy-spring-cloud-goods-service",fallback = GoodsServiceFallback.class)
@FeignClient(name="sy-spring-clound-zuul-gateway")
public interface GoodsService{


    @RequestMapping(value="/goods/catelogGoods", method = RequestMethod.GET)
    public List<Goods> findByCatelogGoodsIdService(@RequestParam(value="cateid",required = false) Integer  cateid, @RequestParam(value="limit",required = false) Integer limit);

    @RequestMapping(value="/goods/good",method = RequestMethod.GET)
    public Goods findByGoodIdService(@RequestParam(value="id") Integer id);

    @RequestMapping(value="/goods/addGoods",method = RequestMethod.POST)
    public void addGoodsService(@RequestBody Goods goods);

    @RequestMapping(value="/goods/searchGoods",method = RequestMethod.GET)
    List<Goods> searchGoodsService(@RequestParam("str") String str);

    @RequestMapping(value="/goods/findUserPublishedAllGoods",method = RequestMethod.GET)
    List<Goods> findUserPublishedAllGoodsService(@RequestParam("userId") Integer userId);

    @RequestMapping(value="/goods/updateGood",method = RequestMethod.POST)
    void updateGoodsService(@RequestBody Goods goods);

    @RequestMapping(value="/goods/delGood",method = RequestMethod.GET)
    void deleteGoodsService(@RequestParam("goodId") Integer goodId);

    @RequestMapping(value="/goods/findGoodByPagination",method = RequestMethod.GET)
    GoodsGrid findGoodsByPagination(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize);

}
