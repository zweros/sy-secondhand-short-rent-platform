package com.szxy.web.controller;

import com.szxy.pojo.User;
import com.szxy.service.UserService;
import com.szxy.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 *  用户 Controller
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    /**
     * 登录用户手机名检查
     * @param phone
     * @return
     */
    @RequestMapping(value="/register",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> registerCheck(String phone){
        return this.userService.userRegistryCheck(phone);
    }

    /**
     * 添加用户
     * @param username
     * @param phone
     * @param password
     */
    @RequestMapping(value="/addUser",method= RequestMethod.POST)
    public String addUser(String username,String phone,String password){
        // TODO: 2019/8/25  没有注册成功或者失败的提示
        this.userService.addUserService(username,phone,password);
        return "redirect:/goods/homeGoods.html";
    }

    /**
     * 用户登录
     * @param phone
     * @param password
     * @return
     */
    @RequestMapping(value="/login",method= RequestMethod.POST)
    public String userLogin(String phone, String password, HttpSession session,HttpServletResponse resp){
        User user = this.userService.userLoginService(phone, password, resp);
        // TODO: 2019/8/25 暂时存放在 session 中
        session.setAttribute("cur_user",user);
        System.out.println("user:登录用户："+user);
        return "redirect:/goods/homeGoods.html";
    }

    /**
     * 用户登录密码检查
     * @param phone
     * @param password
     * @return
     */
    @RequestMapping(value="/password",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,String> userPasswordCheck(String phone, String password){
         return this.userService.userPasswordCheckService(phone,password);
    }

    /**
     * 用户退出
     */
    @RequestMapping(value="/logout",method= RequestMethod.GET)
    public String userLogout(HttpSession session){
        session.invalidate();// 清空 session
        return "redirect:/goods/homeGoods.html";
    }

}
