package com.szxy.web.controller;

import com.szxy.service.impl.GoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther:zwer
 * @Date:2019/8/29 20:16
 * @Description:com.szxy.web.controller
 * @Version:1.0
 **/
@Controller
@RequestMapping("/admin")
public class GoodsManagerController {

    @Autowired
    private GoodsServiceImpl goodsService;

    @RequestMapping(value="/goodsList")
    public String showGoodsList(@RequestParam(defaultValue = "1",required = false) Integer pageNum,
                                @RequestParam(defaultValue = "5",required = false) Integer pageSize, Model model){
        model.addAttribute("goodsGrid",this.goodsService.findGoodsByPaginationService(pageNum,pageSize));
        return "/admin/goods/goodsList";
    }
}
