package com.szxy.service.impl;

import com.szxy.pojo.Admin;
import com.szxy.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 从数据库获取数据
 * @author Administrator
 *
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private AdminService adminService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = this.adminService.findAdminByPhone(username);
		System.out.println(admin);
		//判断用户名是否存在
		if(admin ==null){
			throw new UsernameNotFoundException("该手机号不存在");
		}
		//从数据库中取出对应手机号的密码
		String password =  admin.getPassword();
		//获取该 admin 用户的角色信息
		String roleInfo = admin.getUserRole();
		if(roleInfo != null){
			roleInfo = "ROLE_"+roleInfo;
		}
		System.out.println(roleInfo);
		return
				new User(username,
						password,
						AuthorityUtils.
								commaSeparatedStringToAuthorityList(roleInfo));
	}

}
