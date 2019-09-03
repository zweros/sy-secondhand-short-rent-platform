package com.szxy.provider.service;

import com.szxy.pojo.User;
import com.szxy.utils.UserGrid;

public interface ProviderUserService {

    boolean userCheckService(String phone);

    User findSellerByIdService(Integer id);

    User userLoginService(String phone, String password);

    public int userRegisterService( String username, String phone, String password);

    void updateUserInfoService(User user);

    UserGrid findUserByPaginationService(Integer pageNum, Integer pageSize);
}
