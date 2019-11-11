package com.szxy.security.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

/**
 * 自定义的权限不足错误处理器
 * @author Administrator
 *
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
	

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		//response.setContentType("application/json;charset=utf-8");
		response.setStatus(HttpServletResponseWrapper.SC_FORBIDDEN);//403
		//response.getWriter().print("{\"msg\":\"访问权限不足\"}");
		response.sendRedirect("/admin/403"); //跳转到 403 页面
	}

}
