package com.szxy.config;

import com.szxy.web.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * mvc配置类，也可以实现WebMvcConfigurer接口，这里选择
 * 继承WebMvcConfigurerAdapter，因为只需要重写一下添加
 * 拦截器的方法就可以
 * @Auther:zwer
 * @Date:2019/8/28 17:13
 * @Description:com.szxy.config
 * @Version:1.0
 **/
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    /**
     * 拦截器注册
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getLoginInterceptor())
          .addPathPatterns("/user/**")
          .excludePathPatterns("/user/password")
          .excludePathPatterns("/user/login")
          .excludePathPatterns("/user/register");
        super.addInterceptors(registry);
    }

    /**
     * 自定义登录拦截器
     * @return
     */
    @Bean
    public LoginInterceptor getLoginInterceptor(){
        return  new LoginInterceptor();
    }

}
