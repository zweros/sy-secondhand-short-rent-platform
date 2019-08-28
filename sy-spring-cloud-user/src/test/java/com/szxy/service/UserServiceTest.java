package com.szxy.service;

import com.szxy.pojo.User;
import com.szxy.provider.service.ProviderUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {


    @Autowired
    private ProviderUserService providerUserService;

    @Test
    public void findSellerByIdTest(){
        User user = this.providerUserService.findSellerByIdService(1);
        System.out.println(user);
    }

    @Test
    public void userLoginTest(){
        User user = this.providerUserService.userLoginService("18955752572", "123");
        System.out.println(user);

    }

    @Test
    @Transactional
    @Rollback(false)
    public void userRegisterTest(){
        int i = this.providerUserService.userRegisterService("hello","insert", "123");
        if( i > 0) System.out.println("插入成功 ....");
        else System.out.println("插入失败 .... ");

    }

}
