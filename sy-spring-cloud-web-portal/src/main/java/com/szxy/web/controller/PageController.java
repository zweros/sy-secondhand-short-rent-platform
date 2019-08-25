package com.szxy.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }

    /**
     * 加载网页首部 html 代码
     * @return
     */
    @RequestMapping(value="/goods/goodsHeader",method= RequestMethod.GET)
    private String loadGoodsHeader(){
        return "/goods/goodsHeader";
    }

}
