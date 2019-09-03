package com.szxy.provider.service.impl;

import com.szxy.mapper.AdminMapper;
import com.szxy.pojo.Admin;
import com.szxy.provider.service.ProviderAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther:zwer
 * @Date:2019/9/2 20:38
 * @Description:com.szxy.provider.service.impl
 * @Version:1.0
 **/
@Service
public class ProviderAdminServiceImpl implements ProviderAdminService {

    @Autowired
    private AdminMapper adminMapper;


    @Override
    public void adminRegister(String username, String phone, String password) {
            this.adminMapper.insertAdmin(username,phone,password);
    }

    @Override
    public Admin findAdminByPhone(String phone) {
        return this.adminMapper.selAdminByPhone(phone);
    }

    @Override
    public Admin findAdminInfo(Integer id) {
        return this.adminMapper.selAdminById(id);
    }
}
