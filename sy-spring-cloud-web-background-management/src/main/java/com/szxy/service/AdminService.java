package com.szxy.service;

import com.szxy.pojo.Admin;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther:zwer
 * @Date:2019/9/2 20:15
 * @Description:com.szxy.service
 * @Version:1.0
 **/
@FeignClient(name="sy-spring-clound-zuul-gateway")
public interface AdminService {


    @RequestMapping(value="/user/admin/phone",method=RequestMethod.GET)
    public Admin findAdminByPhone(@RequestParam("phone") String phone);

    @RequestMapping(value="/user/admin/register",method=RequestMethod.POST)
    void adminRegister(@RequestParam("username") String username,
                       @RequestParam("phone") String phone,
                       @RequestParam("password") String password);

    @RequestMapping(value="/user/admin/info",method=RequestMethod.GET)
    Admin findAdminInfo(@RequestParam("id") Integer id);


}
