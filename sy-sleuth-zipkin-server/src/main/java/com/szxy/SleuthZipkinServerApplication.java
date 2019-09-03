package com.szxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

/**
 * @Auther:zwer
 * @Date:2019/9/2 9:17
 * @Description:PACKAGE_NAME
 * @Version:1.0
 **/
@SpringBootApplication
@EnableZipkinServer
public class SleuthZipkinServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SleuthZipkinServerApplication.class,args);
    }
}
