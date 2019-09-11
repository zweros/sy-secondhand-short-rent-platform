package com.szxy.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Component
public class MyServiceImpl{

	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
		
		System.out.println("自定义权限处理逻辑");
		
		//获取权限列表   需要在数据库中存储用户权限信息，才能更好的处理权限
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		//判断当前用户是否有权限访问当前请求uri
		 boolean contains = authorities.contains(new SimpleGrantedAuthority(request.getRequestURI()));
		 System.out.println(request.getRequestURI()+":"+contains);
		 return contains;
	}
	
	
}
