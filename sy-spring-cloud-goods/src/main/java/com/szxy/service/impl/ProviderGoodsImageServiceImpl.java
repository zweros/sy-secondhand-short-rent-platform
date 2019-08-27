package com.szxy.service.impl;

import com.szxy.mapper.GoodsImageMapper;
import com.szxy.pojo.Image;
import com.szxy.service.ProviderGoodsImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderGoodsImageServiceImpl implements ProviderGoodsImageService {

    @Autowired
    private GoodsImageMapper goodsImageMapper;

    @Override
    public Image findGoodsImageByGoodIdService(Integer goodId) {
        return this.goodsImageMapper.selGoodsImageByGoodId(goodId);
    }

    @Override
    public void addGoodsImageService(Image image) {
        this.goodsImageMapper.insertImageMapper(image);
    }
}
