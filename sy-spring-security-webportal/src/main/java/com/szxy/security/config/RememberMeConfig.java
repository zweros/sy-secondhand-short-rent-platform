package com.szxy.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
public class RememberMeConfig {

	@Autowired
	private DataSource dataSource;

	@Bean
	public PersistentTokenRepository setPersistentTokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl=new JdbcTokenRepositoryImpl();
		//设置启动项目是自动建表
		//第二次启动时必须注释表，否则异常Table 'persistent_logins' already exists
		//jdbcTokenRepositoryImpl.setCreateTableOnStartup(true);
		//设置数据源
		jdbcTokenRepositoryImpl.setDataSource(dataSource);;
		return jdbcTokenRepositoryImpl;
	}
}
