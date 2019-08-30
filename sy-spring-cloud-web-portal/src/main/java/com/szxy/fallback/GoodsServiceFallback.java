package com.szxy.fallback;

import com.szxy.pojo.Goods;
import com.szxy.service.GoodsService;
import com.szxy.utils.GoodsGrid;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther:zwer
 * @Date:2019/8/28 11:45
 * @Description:com.szxy.fallback
 * @Version:1.0
 **/
//@Component
public class GoodsServiceFallback implements GoodsService {


    @Override
    public List<Goods> findByCatelogGoodsIdService(Integer cateid, Integer limit) {
        List<Goods> list = new ArrayList<>();
        Goods goods = new Goods();
        goods.setId(-1);
        goods.setName("请求超时");
        list.add(goods);
        return list;
    }

    @Override
    public Goods findByGoodIdService(Integer id) {
        Goods goods = new Goods();
        goods.setId(-1);
        goods.setName("请求超时");
        return goods;
    }

    @Override
    public void addGoodsService(Goods goods) {

    }

    @Override
    public List<Goods> searchGoodsService(String str) {
        List<Goods> list = new ArrayList<>();
        Goods goods = new Goods();
        goods.setId(-1);
        goods.setName("请求超时");
        list.add(goods);
        return list;
    }

    @Override
    public List<Goods> findUserPublishedAllGoodsService(Integer userId) {
        List<Goods> list = new ArrayList<>();
        Goods goods = new Goods();
        goods.setId(-1);
        goods.setName("请求超时");
        list.add(goods);
        return list;
    }

    @Override
    public void updateGoodsService(Goods goods) {

    }

    @Override
    public void deleteGoodsService(Integer goodId) {

    }

    @Override
    public GoodsGrid findGoodsByPagination(Integer page, Integer pageSize) {
        return null;
    }
}
