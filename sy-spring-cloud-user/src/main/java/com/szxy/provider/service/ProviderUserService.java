package com.szxy.provider.service;

import com.szxy.pojo.User;
import org.springframework.web.bind.annotation.RequestParam;

public interface ProviderUserService {

    boolean userCheckService(String phone);

    User findSellerByIdService(Integer id);

    User userLoginService(String phone, String password);

    public int userRegisterService( String username, String phone, String password);
}
