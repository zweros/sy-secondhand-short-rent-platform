package com.szxy.service;

import com.szxy.pojo.Admin;
import com.szxy.provider.service.ProviderAdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProviderAdminServiceTest {

    @Autowired
    private ProviderAdminService adminService;

    @Test
    public void findSellerByIdTest() {
        String phone = "123456";
        Admin admin = this.adminService.findAdminByPhone(phone);
        System.out.println(admin);
    }

    @Test
    public void findSellerByIdTest2() {
        Admin admin = this.adminService.findAdminInfo(1);
        System.out.println(admin);
    }

    @Test
    public void findSellerByIdTest3() {
        this.adminService.adminRegister("test","123","123");
    }

}
