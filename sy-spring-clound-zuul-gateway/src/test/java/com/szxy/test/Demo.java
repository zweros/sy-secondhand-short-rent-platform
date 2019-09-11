package com.szxy.test;

import com.szxy.app.ZullGageWayApplication;
import com.szxy.utils.HttpClientUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther:zwer
 * @Date:2019/9/1 9:04
 * @Description:com.szxy.test
 * @Version:1.0
 **/
@SpringBootTest(classes = {ZullGageWayApplication.class})
@RunWith(SpringRunner.class)
public class Demo {

    @Test
    public void test(){
        //刷新 config-client 项目，让配置文件重新加载
        String url = "http://localhost:80/refresh";
        // 该url必须要使用dopost方式来发送
        HttpClientUtil.doPost(url);
        System.out.println("刷新成功 ....");
    }


}
