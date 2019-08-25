package com.szxy.web.controller;


import com.netflix.discovery.converters.Auto;
import com.szxy.pojo.Goods;
import com.szxy.pojo.Image;
import com.szxy.pojo.User;
import com.szxy.service.GoodsImageService;
import com.szxy.service.GoodsService;
import com.szxy.service.UserService;
import com.szxy.service.impl.GoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsServiceImpl goodsService;

    /**
     * 首页显示物品信息
     * @param model
     * @return
     */
    @RequestMapping("/homeGoods.html")
    public String showHomeGoods(Model model){
        List<Map<Goods, Image>> list = this.goodsService.findAllCatelogGoods();
        for (int i = 0; i < 8; i++) {
            model.addAttribute(i==0?"catelogGoods":"catelogGoods"+i,list.get(i));
        }
        return "/goods/homeGoods";
    }

    /**
     * 展示单个物品详细信息
     * @param id 物品 ID
     * @return
     */
    @RequestMapping("/goodsId/{id}.html")
    public String showDetailGoods(@PathVariable  Integer id,Model model){
        Map<String,Object> map = this.goodsService.findDetailGoods(id);
        model.addAttribute("goods",map.get("goods"));
        model.addAttribute("img",map.get("img"));
        model.addAttribute("seller",map.get("seller"));
        return "/goods/detailGoods";
    }

}
