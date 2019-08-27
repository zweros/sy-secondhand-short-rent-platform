package com.szxy.provider.service;

import com.szxy.pojo.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/user")
public interface ProviderFeignUserService {

    /**
     * 用户登录检查
     * @param phone
     * @return
     */
    @RequestMapping(value="/registerCheck",method = RequestMethod.GET)
    public int registerCheck(@RequestParam("phone") String phone);

    /**
     * 用户注册
     * @param username
     * @param phone
     * @param password
     */
    @RequestMapping(value="/register",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    public int userRegisterService(@RequestParam("username") String username,
                             @RequestParam("phone")  String phone,
                             @RequestParam("password") String password);

    /**
     * 用户登录
     * @param phone
     * @param password
     * @return
     */
    @RequestMapping(value="/login",method = RequestMethod.POST,
                        consumes = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    public User userLoginService(@RequestParam("phone")String phone,
                                    @RequestParam("password")String password);

    /**
     * 查询卖家信息
     * @param id
     * @return
     */
    @RequestMapping(value="/getSellerInfo",method = RequestMethod.POST,
                consumes = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    public User findSellerInfoByIdService(@RequestParam("id") Integer id);




}
