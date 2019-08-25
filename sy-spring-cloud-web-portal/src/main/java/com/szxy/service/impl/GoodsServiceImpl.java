package com.szxy.service.impl;

import com.szxy.pojo.Goods;
import com.szxy.pojo.Image;
import com.szxy.pojo.User;
import com.szxy.service.GoodsImageService;
import com.szxy.service.GoodsService;
import com.szxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务消费者   假的接口实现类
 */

@Service
public class GoodsServiceImpl {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsImageService goodsImageService;
    @Autowired
    private UserService userService;

    /**
     * 查看所有物品分类的物品信息
     * 并每个类别显示前 6 条
     * @return
     */
    public   List< Map<Goods, Image>>  findAllCatelogGoods() {
        List< Map<Goods, Image>> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            //查询所有分类商品，按发布时间倒序排序，每个类别显示 6 个
            List<Goods> goods = this.goodsService.findByCatelogGoodsIdService(i == 0 ? null : i, 6);
            Map<Goods, Image> goodAndImgMap = new HashMap<>();
            for (Goods good : goods) {
                Image img = this.goodsImageService.findGoodsImageByGoodIdService(good.getId());
                goodAndImgMap.put(good, img);
            }
            list.add(goodAndImgMap);
        }
        return list;
    }

    /**
     * 查询物品详细信息
     * @param id  物品 ID
     * @return
     */
    public  Map<String,Object> findDetailGoods(Integer id){
        Goods goods = this.goodsService.findByGoodIdService(id);
        Image img = this.goodsImageService.findGoodsImageByGoodIdService(goods.getId());
        User seller = this.userService.findSellerInfoByIdService(goods.getUserId());
        Map<String, Object> map = new HashMap<>();
        map.put("goods",goods);
        map.put("img",img);
        map.put("seller",seller);
        return map;
    }

}
