package com.szxy.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page) {
        return page;
    }

    /**
     * 加载网页首部 html 代码
     *
     * @return
     */
    @RequestMapping(value = "/goods/goodsHeader", method = RequestMethod.GET)
    public String loadGoodsHeader() {
        return "/goods/goodsHeader";
    }

    /**
     *  显示首页
     *
     * @return
     */
    @RequestMapping(value = {"/","/goods/homeGoods"}, method = RequestMethod.GET)
    public String showHome() {
        return "forward:/goods/homeGoods.html";
    }

    /**
     * 加载导航栏信息
     * @return
     */
    @RequestMapping(value = "/goods/catelogNavigation", method = RequestMethod.GET)
    public String showCatelogNavigation() {
        return "/goods/catelogNavigation";
    }


}
