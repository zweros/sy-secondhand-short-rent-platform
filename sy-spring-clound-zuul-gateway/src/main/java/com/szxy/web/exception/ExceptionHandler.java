package com.szxy.web.exception;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther:zwer
 * @Date:2019/9/1 9:45
 * @Description:com.szxy.web.exception
 * @Version:1.0
 **/
@RestController
public class ExceptionHandler implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error"; //若出现错误，交给下面的处理器处理
    }

    /**
     *  处理 网关 error 类型过滤器的错误处理
     * @return
     */
    @RequestMapping(value="/error")
    public String error() {
        return "{\"result\":\"500 error!!\"}";
    }

}