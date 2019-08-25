package com.szxy.service;

import com.szxy.pojo.Image;

public interface ProviderGoodsImageService {

    /**
     * 根据商品 ID 查询商品信息
     * @param goodId
     * @return
     */
    public Image findGoodsImageByGoodIdService(Integer goodId);

}
