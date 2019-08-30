package com.szxy.service.impl;

import com.szxy.mapper.GoodsMapper;
import com.szxy.pojo.Goods;
import com.szxy.service.ProviderGoodsService;
import com.szxy.utils.GoodsGrid;
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

    @Override
    public void deleteGoodService(Integer goodId) {
        this.goodsMapper.delGoodsMapper(goodId);
    }

    @Override
    public GoodsGrid findGoodsByPaginationService(Integer page, Integer pageSize) {
        GoodsGrid goodsGrid = new GoodsGrid();
         goodsGrid.setCurrent(page);
         goodsGrid.setRowCount(pageSize);
         Integer start = (page-1)*pageSize;
         Integer end = pageSize;
         List<Goods> goodsList = this.goodsMapper.selGoodsByPaginationMapper(start,end);
         goodsGrid.setRows(goodsList);
         int total = this.goodsMapper.selGoodsAllCountMapper();
         goodsGrid.setTotal(total);
        return goodsGrid;
    }
}
