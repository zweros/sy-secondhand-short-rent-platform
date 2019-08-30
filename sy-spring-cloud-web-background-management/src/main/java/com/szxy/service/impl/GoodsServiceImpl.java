package com.szxy.service.impl;

import com.szxy.provider.service.ProviderFeignGoodsService;
import com.szxy.utils.GoodsGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther:zwer
 * @Date:2019/8/29 20:33
 * @Description:com.szxy.service.impl
 * @Version:1.0
 **/
@Service
public class GoodsServiceImpl  {

    @Autowired
    private ProviderFeignGoodsService providerFeignGoodsService;

    public GoodsGrid findGoodsByPaginationService(Integer page,Integer pageSize){
        return this.providerFeignGoodsService.findGoodsByPagination(page,pageSize);
    }

}
