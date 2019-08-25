package com.szxy.provider.service.impl;

import com.szxy.mapper.UserMapper;
import com.szxy.pojo.User;
import com.szxy.provider.service.ProviderUserService;
import com.szxy.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderUserServiceImpl  implements ProviderUserService {

    @Autowired
    private UserMapper userMapper;

    public boolean userCheckService(String phone) {
        User user = this.userMapper.selectByUserPhoneMapper(phone);
        if(user != null){
            return true;
        }
        return false;
    }

    @Override
    public User findSellerByIdService(Integer id) {
        User user = this.userMapper.selSellerInfoByIdMapper(id);
        if(user != null){
            return user;
        }
        return null;
    }

    @Override
    public User userLoginService(String phone, String password) {
        User user = this.userMapper.selectByUserPhoneMapper(phone);
        if(user != null){
            String md5 = MD5.md5(password);
            if(user.getPassword().equals(md5)){
                return user;
            }
        }
        return null;
    }

    @Override
    public int userRegisterService(String username, String phone, String password) {
        return this.userMapper.inserUserMapper(username,phone, MD5.md5(password));
    }
}
