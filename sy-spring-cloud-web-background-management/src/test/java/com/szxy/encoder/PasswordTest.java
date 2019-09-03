package com.szxy.encoder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther:zwer
 * @Date:2019/9/2 17:33
 * @Description:com.szxy.encoder
 * @Version:1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class PasswordTest {

    @Autowired
    private PasswordEncoder encoder;

    @Test
    public void test(){
        String encode = this.encoder.encode("123456");
        System.out.println(encode);
    }

    @Test
    public void test2(){
       String sre = "$2a$10$TlyZfKf.lDDk5KBgRTFT5upQfd1I/EYmesrCyX4dWkiAh7ZQLL42m";
        boolean flag = this.encoder.matches("123456", sre);
        System.out.println(flag);
    }

}
