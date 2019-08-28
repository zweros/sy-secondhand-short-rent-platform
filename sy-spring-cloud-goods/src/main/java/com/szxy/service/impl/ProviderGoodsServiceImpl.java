package com.szxy.service.impl;

import com.szxy.mapper.GoodsMapper;
import com.szxy.pojo.Goods;
import com.szxy.service.ProviderGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderGoodsServiceImpl implements ProviderGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> findByCatelogGoodsIdService(Integer cateid, Integer limit) {
        return this.goodsMapper.selByCatelogGoodsId(cateid,limit);
    }

    @Override
    public Goods findByGoodIdService(Integer id) {
        return this.goodsMapper.selByGoodId(id);
    }

    @Override
    public void addGoodsService(Goods goods) {
        this.goodsMapper.insertGoodsMapper(goods);
    }

    @Override
    public List<Goods> searchGoodsService(String str) {
        return this.goodsMapper.findGoodsByNameMapper(str);
    }

    @Override
    public List<Goods> findUserPublishedAllGoodsService(Integer userId) {
        return this.goodsMapper.selUserPublishedAllGoodsMapper(userId);
    }

    @Override
    public void updateGoodsService(Goods goods) {
        this.goodsMapper.updateGoodsMapper(goods);
    }
}
