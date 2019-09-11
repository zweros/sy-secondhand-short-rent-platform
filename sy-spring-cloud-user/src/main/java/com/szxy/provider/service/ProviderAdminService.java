package com.szxy.provider.service;

import com.szxy.pojo.Admin;

/**
 * @Auther:zwer
 * @Date:2019/9/2 20:33
 * @Description:com.szxy.provider.service
 * @Version:1.0
 **/
public interface ProviderAdminService {

    void adminRegister(String username,String phone,String password);

    Admin findAdminByPhone(String phone);

    Admin findAdminInfo(Integer id);

}
