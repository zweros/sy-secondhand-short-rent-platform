package com.szxy.web.controller;

import com.szxy.pojo.Catelog;
import com.szxy.pojo.Comments;
import com.szxy.pojo.Goods;
import com.szxy.pojo.Image;
import com.szxy.provider.service.ProviderFeignGoodsCatelogService;
import com.szxy.provider.service.ProviderFeignGoodsCommentsService;
import com.szxy.provider.service.ProviderFeignGoodsImageService;
import com.szxy.provider.service.ProviderFeignGoodsService;
import com.szxy.service.ProviderGoodsCatelogService;
import com.szxy.service.ProviderGoodsCommentsService;
import com.szxy.service.ProviderGoodsService;
import com.szxy.service.impl.ProviderGoodsImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GoodsController implements ProviderFeignGoodsService,
                ProviderFeignGoodsImageService ,
                ProviderFeignGoodsCatelogService,
                ProviderFeignGoodsCommentsService {

    @Autowired
    private ProviderGoodsService providerGoodsService;
    @Autowired
    private ProviderGoodsImageServiceImpl goodsImageService;
    @Autowired
    private ProviderGoodsCatelogService providerGoodsCatelogService;
    @Autowired
    private ProviderGoodsCommentsService providerGoodsCommentsService;

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

    @Override
    public void addGoodsService(@RequestBody Goods goods) {
         this.providerGoodsService.addGoodsService(goods);
    }

    @Override
    public List<Goods> searchGoodsService(String str) {
        return this.providerGoodsService.searchGoodsService(str);
    }

    @Override
    public List<Goods> findUserPublishedAllGoodsService(@RequestParam("userId") Integer userId) {
        return this.providerGoodsService.findUserPublishedAllGoodsService(userId);
    }

    @Override
    public void updateGoodsService(@RequestBody  Goods goods) {
        this.providerGoodsService.updateGoodsService(goods);
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

    /**
     * 添加商品图片信息
     * @param image
     */
    @Override
    public void addGoodsImageService(@RequestBody  Image image) {
        this.goodsImageService.addGoodsImageService(image);
    }

    /**
     * 查找所有物品类别
     * @return
     */
    @Override
    public List<Catelog> findAllCatelog() {
        return this.providerGoodsCatelogService.findAllCatelogService();
    }

    /**
     * 根据物品类型 ID 查询物品类型信息
     * @param id
     * @return
     */
    @Override
    public Catelog findCatelogByIdService(@RequestParam(value="id")Integer id) {
        return this.providerGoodsCatelogService.findCatelogById(id);
    }

    /**
     * 根据物品的 ID 查询对应评论
     * @param goodsId
     * @return
     */
    @Override
    public List<Comments> findCommentsByGoodsIdService(@RequestParam("goodsId") Integer goodsId) {
        return this.providerGoodsCommentsService.findCommentsByGoodsIdService(goodsId);
    }

    /**
     *
     * @param userId
     * @return
     */
    @Override
    public List<Comments> findCommentsByUserIdService(@RequestParam("userId") Integer userId) {
        return this.providerGoodsCommentsService.findCommentsByUserIdService(userId);
    }

    @Override
    public boolean addCommentsService(@RequestBody Comments comment) {
        return this.providerGoodsCommentsService.addCommentsService(comment);
    }
}
