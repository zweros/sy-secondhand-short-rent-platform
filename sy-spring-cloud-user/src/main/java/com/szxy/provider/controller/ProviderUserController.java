package com.szxy.provider.controller;

import com.szxy.pojo.User;
import com.szxy.provider.service.ProviderFeignUserService;
import com.szxy.provider.service.ProviderUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderUserController implements ProviderFeignUserService {

    @Autowired
    private ProviderUserService providerUserService;

    @Override
    public int registerCheck(@RequestParam  String phone) {
        boolean flag = this.providerUserService.userCheckService(phone);
        if(flag){return 1;}else{return 0;}
    }

    @Override
    public int userRegisterService(@RequestParam("username") String username,
                             @RequestParam("phone")  String phone,
                             @RequestParam("password") String password)  {
        return  this.providerUserService.userRegisterService(username, phone, password);
    }

    @Override
    public User userLoginService(@RequestParam("phone")String phone,
                                 @RequestParam("password")String password) {
        return this.providerUserService.userLoginService(phone,password);
    }

    @Override
    public User findSellerInfoByIdService(@RequestParam("id") Integer id) {
        return this.providerUserService.findSellerByIdService(id);
    }
}
