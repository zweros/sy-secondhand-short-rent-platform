package com.szxy.web.controller;


import com.netflix.discovery.converters.Auto;
import com.szxy.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/homeGoods")
    public String showHomeGoods(Model model){
        //查询最新发布 catelogGoods
        model.addAttribute("catelogGoods",goodsService.findByCatelogGoodsIdService(0,6));
        //查询闲置数码
        model.addAttribute("catelogGoods1",goodsService.findByCatelogGoodsIdService(1,6));
        //查询校园代步
        //查询电器日用
        return "/goods/homeGoods";
    }

}
