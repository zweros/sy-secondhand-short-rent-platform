package com.szxy.web.controller;


import com.szxy.pojo.User;
import com.szxy.service.impl.GoodsServiceImpl;
import com.szxy.service.impl.UserServiceImpl;
import com.szxy.utils.UserGrid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther:zwer
 * @Date:2019/8/30 15:10
 * @Description:com.szxy.web.controller
 * @Version:1.0
 **/
@Controller
@RequestMapping("/admin")
public class AdminController {

    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private GoodsServiceImpl goodsService;
    @Autowired
    private UserServiceImpl userService;

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    @RequestMapping(value="/getUser",method= RequestMethod.GET)
    @ResponseBody
    public User getUser(Integer id){
        return this.userService.findUserbyIdService(id  );
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @RequestMapping(value="/updateUser",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,String >  updateUser(User user){
         this.userService.updateUserService(user);
         Map<String,String >  map = new HashMap<>();
         map.put("msg","ok");
         return map;
     }



    /**
     * 分页查找用户信息
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping(value="/userList")
    public  String showUserList(@RequestParam(defaultValue = "1",required = false) Integer pageNum,
                                @RequestParam(defaultValue = "5",required = false) Integer pageSize,Model model){
        UserGrid userGrid = this.userService.findUserByPaginationService(pageNum,pageSize);
        model.addAttribute("userGrid",userGrid);
        System.out.println("userList");
        return "/admin/user/userList";
    }

    /**
     * 添加用户信息
     */
    @RequestMapping(value="/user/addUser",method= RequestMethod.POST)
    public String addUser(User user,Model model){
        try {
            String username = new String(user.getUsername().getBytes("ISO-8859-1"), "utf-8");
            user.setUsername(username);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.userService.addUserService(user);
        User user2 = this.userService.findUserByPhone(user.getPhone());
        model.addAttribute("user",user2);
        model.addAttribute("formAction","/admin/back/user/update");
        return "/admin/user/userAdd";
    }

    /**
     * 添加用户信息成功，添加密码信息
     * @param user
     * @return
     */
    @RequestMapping("/back/user/update")
    public String backUpdateUser(User user){
        try {
            String username = new String(user.getUsername().getBytes("ISO-8859-1"), "utf-8");
            user.setUsername(username);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        logger.info("userBack:"+user);
        this.userService.updateUserService(user);
        return "redirect:/admin/userList";
    }


    /**
     * 分页查找二手物品信息
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping(value="/goodsList")
    public String showGoodsList(@RequestParam(defaultValue = "1",required = false) Integer pageNum,
                                @RequestParam(defaultValue = "5",required = false) Integer pageSize, Model model){
        model.addAttribute("goodsGrid",this.goodsService.findGoodsByPaginationService(pageNum,pageSize));
        System.out.println("goodList");
        return "/admin/goods/goodsList";
    }


}
