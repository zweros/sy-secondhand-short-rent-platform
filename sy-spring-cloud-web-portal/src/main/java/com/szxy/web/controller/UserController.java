package com.szxy.web.controller;

import com.szxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/register")
    @ResponseBody
    public Map<String,Object> registerCheck(String phone){
        Map<String,Object> map =
                new HashMap<String,Object>();
       int flag = this.userService.registerCheck(phone);
       if(flag > 0){
           map.put("flag",true);
       } else{
           map.put("flag",false);
       }
        return map;
    }

}
