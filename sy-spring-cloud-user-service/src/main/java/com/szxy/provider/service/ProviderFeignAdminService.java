package com.szxy.provider.service;

import com.szxy.pojo.Admin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther:zwer
 * @Date:2019/9/2 20:15
 * @Description:com.szxy.provider.service
 * @Version:1.0
 **/
public interface ProviderFeignAdminService {

    @RequestMapping(value="/admin/phone",method=RequestMethod.GET)
    public Admin findAdminByPhone(@RequestParam("phone") String phone);

    @RequestMapping(value="/admin/register",method=RequestMethod.POST)
    void adminRegister(@RequestParam("username") String username,
                       @RequestParam("phone") String phone,
                       @RequestParam("password") String password);

    @RequestMapping(value="/admin/info",method=RequestMethod.GET)
    Admin findAdminInfo(@RequestParam("id") Integer id);

}
