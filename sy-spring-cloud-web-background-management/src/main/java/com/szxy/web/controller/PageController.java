package com.szxy.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther:zwer
 * @Date:2019/8/29 20:14
 * @Description:com.szxy.web.controller
 * @Version:1.0
 **/
@Controller
public class PageController {

    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page) {
        return page;
    }



}
