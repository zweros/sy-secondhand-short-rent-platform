package com.szxy.web.controller;

import com.szxy.pojo.Goods;
import com.szxy.pojo.Image;
import com.szxy.provider.service.ProviderFeignGoodsImageService;
import com.szxy.provider.service.ProviderFeignGoodsService;
import com.szxy.service.ProviderGoodsImageService;
import com.szxy.service.ProviderGoodsService;
import com.szxy.service.impl.ProviderGoodsImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GoodsController implements ProviderFeignGoodsService, ProviderFeignGoodsImageService {

    @Autowired
    private ProviderGoodsService providerGoodsService;
    @Autowired
    private ProviderGoodsImageServiceImpl goodsImageService;
    /**
     * 根据物品类别查询商品信息
     * @param cateid 物品类别
     * @param limit  显示的物品数量
     * @return
     */
    @Override
    public List<Goods> findByCatelogGoodsIdService(
            @RequestParam(value="cateid",required = false) Integer cateid,
            @RequestParam(value="limit",required = false)  Integer limit) {
        return this.providerGoodsService.findByCatelogGoodsIdService(cateid,limit);
    }

    /**
     * 根据物品 ID 查询物品信息
     * @param id
     * @return
     */
    @Override
    public Goods findByGoodIdService(@RequestParam("id") Integer id) {
        return this.providerGoodsService.findByGoodIdService(id);
    }

    /**
     * 根据物品 ID 查询物品的图片信息
     * @param goodId
     * @return
     */
    @Override
    public Image findGoodsImageByGoodIdService(@RequestParam("goodId") Integer goodId) {
        return this.goodsImageService.findGoodsImageByGoodIdService(goodId);
    }
}