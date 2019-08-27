package com.szxy.service;

import com.szxy.pojo.Image;

public interface ProviderGoodsImageService {

    /**
     * 根据物品ID 查询商品信息
     * @param goodId
     * @return
     */
    public Image findGoodsImageByGoodIdService(Integer goodId);

    /**
     * 添加物品图片
     * @param image
     */
    void addGoodsImageService(Image image);
}
