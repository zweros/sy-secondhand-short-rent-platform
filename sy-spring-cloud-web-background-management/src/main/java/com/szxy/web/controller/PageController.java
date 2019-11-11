package com.szxy.web.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther:zwer
 * @Date:2019/8/29 20:14
 * @Description:com.szxy.web.controller
 * @Version:1.0
 **/
@Controller
public class PageController {

    /**
     * 页面跳转
     * @param page
     * @return
     */
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page) {
        return page;
    }

    /**
     * 显示登陆界面
     *
     * @return
     */
    @RequestMapping("/")
    public String showPage() {
        return "/admin/login";
    }

    /**
     * 显示登录页面
     * @return
     */
    @RequestMapping("/admin/login")
    public String showLogin() {

        return "/admin/login";
    }

    /**
     * 登录失败后跳转的页面
     * @return
     */
    @RequestMapping("/admin/loginFail")
    public String loginFail(Model model) {
        model.addAttribute("error","用户名或密码错误");
        return "/admin/login";
    }

    /**
     * 登录成功后跳转的页面
     * @return
     */
    @RequestMapping("/admin/loginSuccess")
    public String loginSuccess() {
        return "redirect:/admin/index";
    }

    /**
     * 显示主页
     * @return
     */
    @Secured("ROLE_admin")
    @RequestMapping("/admin/index")
    public String showIndex(){
        return "/admin/index";
    }

    /**
     * 显示 403 页面
     * @return
     */
    @RequestMapping("/admin/403")
    public String showException(){
        return "/admin/403";
    }

    /**
     * 显示添加用户页面
     * @return
     */
    @RequestMapping("/admin/user/userAdd")
    public String addUser(Model model){
        model.addAttribute("formAction","/admin/user/addUser");
        return "/admin/user/userAdd";
    }


}
