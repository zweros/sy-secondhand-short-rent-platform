package com.szxy.service.impl;

import com.szxy.pojo.User;
import com.szxy.service.UserService;
import com.szxy.utils.UserGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther:zwer
 * @Date:2019/8/31 19:30
 * @Description:com.szxy.service.impl
 * @Version:1.0
 **/
@Service
public class UserServiceImpl  {

    @Autowired
    private UserService userService;

    /**
     * 分页查询用户信息
     * @param pageNum   当前的页数
     * @param pageSize  每页显示的分页书
     * @return
     */
    public UserGrid findUserByPaginationService(Integer pageNum, Integer pageSize) {
        UserGrid userGrid= this.userService.findUserByPaginationService(pageNum,pageSize);
        return userGrid;
    }

    /**
     * 根据用户 ID 查询用户信息
     * @param userId
     * @return
     */
    public User findUserbyIdService(Integer userId) {
        User user = this.userService.findSellerInfoByIdService(userId);
        return user;

    }

    /**
     * 更新用户信息
     * @param user
     */
    public void updateUserService(User user) {
        this.userService.updateUserInfoService(user);
    }
}
