package com.szxy.refresh;

import com.szxy.ConfigServerApplication;
import com.szxy.utils.HttpClientUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther:zwer
 * @Date:2019/9/1 11:22
 * @Description:com.szxy.refresh
 * @Version:1.0
 **/
@SpringBootTest(classes = {ConfigServerApplication.class})
@RunWith(SpringRunner.class)
public class Demo {

    @Test
    public void test(){
        String url = "http://localhost:9050/bus/refresh";
        HttpClientUtil.doPost(url);
    }

}
