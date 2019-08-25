package com.szxy.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }

    @RequestMapping("/goods/goodsHeader")
    public String loadGoodsHeader(){
        return "/goods/goodsHeader";
    }

}
