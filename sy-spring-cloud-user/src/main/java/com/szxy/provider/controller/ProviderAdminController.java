package com.szxy.provider.controller;

import com.szxy.pojo.Admin;
import com.szxy.provider.service.ProviderAdminService;
import com.szxy.provider.service.ProviderFeignAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther:zwer
 * @Date:2019/9/2 20:17
 * @Description:com.szxy.provider.controller
 * @Version:1.0
 **/
@RestController
public class ProviderAdminController implements ProviderFeignAdminService {

    @Autowired
    private ProviderAdminService adminService;

    /**
     * 管理员登录
     * @param phone   管理员手机号
     * @return
     */
    @Override
    public Admin findAdminByPhone(@RequestParam("phone") String phone) {
        Admin admin =
                this.adminService.findAdminByPhone(phone);
        return admin;
    }

    @Override
    public void adminRegister(@RequestParam("username") String username,
                              @RequestParam("phone") String phone,
                              @RequestParam("password") String password) {
        this.adminService.adminRegister(username,phone,password);
    }

    @Override
    public Admin findAdminInfo(@RequestParam("id") Integer id) {
        return this.adminService.findAdminInfo(id);
    }
}
