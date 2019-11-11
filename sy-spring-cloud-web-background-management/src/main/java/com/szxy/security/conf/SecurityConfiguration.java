package com.szxy.security.conf;

import com.szxy.security.handler.MyAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * @Auther:zwer
 * @Date:2019/8/31 16:44
 * @Description:com.szxy.conf
 * @Version:1.0
 **/
@Configuration
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {


    @Autowired
    private MyAccessDeniedHandler accessDecisionHandler;

  /*  @Autowired
    private PersistentTokenRepository tokenRepository;
*/
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 必须制定一个PasswordEncoder用于密码加密
     * @return
     */
    @Bean
    public PasswordEncoder setEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //登录认证
        http.formLogin()
                .usernameParameter("phone")                   //自定义用户名字段
                .passwordParameter("password")                //自定义密码字段
                .loginPage("/admin/login")					  //设置自定义登录页面，使用绝对路径
                .loginProcessingUrl("/admin/toLogin")		  //设置登录请求url   此处是post请求，不能用来请求页面。会报405错误
                .failureUrl("/admin/loginFail")				  //登录失败请求转发
                .successForwardUrl("/admin/loginSuccess");	  //登录成功请求转发。

        //退出
        http.logout()
                .logoutSuccessUrl("/admin/login"); //退出到登录界面

        //授权
        http.authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/font-awesome/**").permitAll()
                .antMatchers("/fonts/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/upload/**").permitAll()
                .antMatchers("/admin/login").permitAll()				//请求登录页面的请求不需要认证。
                .antMatchers("/admin/toLogin").permitAll()				//登录的请求不需要认证。
                .antMatchers("/admin/loginFail").permitAll()            //登录失败请求不需要认证
                .antMatchers("/admin/updateUser").hasRole("superAdmin") //只有超级管理员才可以修改
                .antMatchers("/admin/user/userAdd").hasRole("superAdmin")
                .antMatchers("/admin/user/addUser").hasRole("superAdmin")
                .antMatchers("/admin/back/user/update").hasRole("superAdmin")
                .anyRequest().authenticated();							//所有请求都需要认证
        //异常处理
        http.
                exceptionHandling().
                accessDeniedHandler(new MyAccessDeniedHandler());
        //关闭 csrf 防护
        http.csrf().disable();

    }

    /**
     * 解决乱码问题
     * @return
     */
    @Bean
    public CharacterEncodingFilter setEncodingFilter() {
        return new CharacterEncodingFilter("utf-8", true);
    }


}
