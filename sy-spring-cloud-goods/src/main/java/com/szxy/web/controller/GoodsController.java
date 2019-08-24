package com.szxy.web.controller;

import com.szxy.pojo.Goods;
import com.szxy.provider.service.ProviderFeignGoodsService;
import com.szxy.service.ProviderGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GoodsController implements ProviderFeignGoodsService {

    @Autowired
    private ProviderGoodsService providerGoodsService;

    @Override
    public List<Goods> findByCatelogGoodsIdService(
                            @RequestParam("cateid") Integer cateid,
                            @RequestParam("limit") Integer limit) {
        return this.providerGoodsService.findByCatelogGoodsIdService(cateid,limit);
    }

    @Override
    public Goods findByGoodIdService(@RequestParam("id") Integer id) {
        return this.providerGoodsService.findByGoodIdService(id);
    }
}
