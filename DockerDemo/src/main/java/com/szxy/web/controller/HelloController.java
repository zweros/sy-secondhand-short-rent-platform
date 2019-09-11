package com.szxy.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther:zwer
 * @Date:2019/9/1 17:25
 * @Description:com.szxy.web.controller
 * @Version:1.0
 **/
@RestController
public class HelloController {

    public HelloController(){
        System.out.println("Hello ......");
    }

    @RequestMapping(value = {"/","/hello"}, method = RequestMethod.GET)
    public String sayHello() {
        return "docker hello";
    }

}
