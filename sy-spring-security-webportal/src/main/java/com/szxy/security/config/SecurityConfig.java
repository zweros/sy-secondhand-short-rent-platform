package com.szxy.security.config;

import com.szxy.security.handler.MyAccessDeniedHandler;
import com.szxy.security.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.filter.CharacterEncodingFilter;

import cn.xyl.security.handler.MyAccessDeniedHandler;
import cn.xyl.security.service.impl.UserDetailServiceImpl;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private MyAccessDeniedHandler accessDecisionHandler;
	
	@Autowired
	private PersistentTokenRepository tokenRepository;
	
	@Autowired
	private UserDetailServiceImpl userDetailsService;
	
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
		
		System.out.println("======================");
		http.formLogin()
		.loginPage("/admin/login")						//设置自定义登录页面，使用绝对路径
		.loginProcessingUrl("/admin/toLogin")				//设置登录请求url   此处是post请求，不能用来请求页面。会报405错误
		.failureUrl("/admin/loginFail")					//登录失败请求转发
		.successForwardUrl("/admin/loginSuccess");		//登录成功请求转发。
		
		
		http.authorizeRequests()
		.antMatchers("/admin/images/**").permitAll()				//请求登录页面的请求不需要认证。
		.antMatchers("/admin/login").permitAll()				//请求登录页面的请求不需要认证。
		.antMatchers("/admin/css/**").permitAll()				//请求登录页面的请求不需要认证。
		.antMatchers("/admin/js/**").permitAll()				//请求登录页面的请求不需要认证。
		.antMatchers("/admin/editormd/**").permitAll()				//请求登录页面的请求不需要认证。
		.antMatchers("/admin/plugins/**").permitAll()				//请求登录页面的请求不需要认证。
		.antMatchers("/error/**").permitAll()				//请求登录页面的请求不需要认证。
		.antMatchers("/site/**").permitAll()				//请求登录页面的请求不需要认证。
		.antMatchers("/admin/toLogin").access("permitAll")				//请求登录页面的请求不需要认证。
		.anyRequest().authenticated();							//所有请求都需要认证
		
		
		http.exceptionHandling()
			//.accessDeniedPage("/403.html")				//底层调用默认的accessDeniedHandler，使用请求转发到错误页面。
			.accessDeniedHandler(accessDecisionHandler);  	//设置自定义的403异常处理
		
		
		http.rememberMe()
		.rememberMeParameter("rememberMe")		//设置form表单中rememberme的name属性值   默认为remember-me
		.tokenRepository(tokenRepository)					//设置持久化的tokenRepository
		.tokenValiditySeconds(3*60*60*24)				//过期时间,
		.userDetailsService(userDetailsService);	//将登陆请求交给哪个对象处理
		
		
		
		http.logout()
			.logoutUrl("/admin/logout")			//自定义退出登录请求。
			.logoutSuccessUrl("/admin/login");	//自定义退出成功后跳转的url
		
		http.csrf().disable();
		System.out.println("+++++++++++++++++++++++++++++++");
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
