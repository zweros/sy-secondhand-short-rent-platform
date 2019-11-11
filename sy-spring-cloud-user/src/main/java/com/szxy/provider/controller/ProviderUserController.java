package com.szxy.provider.controller;

import com.szxy.pojo.Focus;
import com.szxy.pojo.Purse;
import com.szxy.pojo.User;
import com.szxy.provider.service.ProviderFeignUserService;
import com.szxy.provider.service.ProviderPurseService;
import com.szxy.provider.service.ProviderUserFocusService;
import com.szxy.provider.service.ProviderUserService;
import com.szxy.utils.UserGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProviderUserController implements ProviderFeignUserService {

    @Autowired
    private ProviderUserService providerUserService;
    @Autowired
    private ProviderUserFocusService providerUserFocusService;
    @Autowired
    private ProviderPurseService providerPurseService;

    @Override
    public int registerCheck(@RequestParam  String phone) {
        boolean flag = this.providerUserService.userCheckService(phone);
        if(flag){return 1;}else{return 0;}
    }

    @Override
    public int userRegisterService(@RequestParam("username") String username,
                             @RequestParam("phone")  String phone,
                             @RequestParam("password") String password)  {
        return  this.providerUserService.userRegisterService(username, phone, password);
    }

    @Override
    public User userLoginService(@RequestParam("phone")String phone,
                                 @RequestParam("password")String password) {
        return this.providerUserService.userLoginService(phone,password);
    }

    @Override
    public User findSellerInfoByIdService(@RequestParam("id") Integer id) {
        return this.providerUserService.findSellerByIdService(id);
    }

    @Override
    public void addGoodsFocusService(@RequestParam("goodId") Integer goodId, @RequestParam("uid")Integer uid){
        this.providerUserFocusService.addGoodsFocusService(goodId,uid);
    }

    @Override
    public List<Focus> findGoodsFocusByUserIdService(@RequestParam("userId") Integer userId) {
        return this.providerUserFocusService.findGoodsFocusByUserIdService(userId);
    }

    @Override
    public void updateUserInfoService(@RequestBody User user) {
        this.providerUserService.updateUserInfoService(user);
    }

    @Override
    public void deleteFocusGoodsService(Integer goodId) {
        this.providerUserFocusService.delFocusGoodsService(goodId);
    }

    @Override
    public UserGrid findUserByPaginationService(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize) {
        return this.providerUserService.findUserByPaginationService(pageNum,pageSize);
    }

    @Override
    public User findUserByPhoneService(@RequestParam("phone") String phone) {
        return this.providerUserService.findUserByPhoneService(phone);
    }

    @Override
    public void updatePurseByUserId(@RequestBody  Purse purse) {
        this.providerPurseService.updatePurseByUserId(purse);
    }

    @Override
    public Purse findPurseByUserId(Integer userid) {
        return this.providerPurseService.findPurseByUserId(userid);
    }

    @Override
    public void addPurse(@RequestBody  Integer userId) {
        this.providerPurseService.addPurse(userId);
    }

    @Override
    public void addUserService(@RequestBody User user) {
        this.providerUserService.addUserService(user);
    }

}
