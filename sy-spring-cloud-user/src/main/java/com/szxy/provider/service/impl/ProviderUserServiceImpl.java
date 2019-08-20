package com.szxy.provider.service.impl;

import com.szxy.mapper.UserMapper;
import com.szxy.pojo.User;
import com.szxy.provider.service.ProviderUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderUserServiceImpl  implements ProviderUserService {

    @Autowired
    private UserMapper userMapper;

    public boolean userCheckService(String phone) {
        User user = this.userMapper.selectByUserPhone(phone);
        if(user != null){
            return true;
        }
        return false;
    }
}
