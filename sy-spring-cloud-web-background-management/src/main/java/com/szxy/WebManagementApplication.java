package com.szxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @Auther:zwer
 * @Date:2019/8/29 20:13
 * @Description:com.szxy
 * @Version:1.0
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableDiscoveryClient
public class WebManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebManagementApplication.class,args);
    }
}
