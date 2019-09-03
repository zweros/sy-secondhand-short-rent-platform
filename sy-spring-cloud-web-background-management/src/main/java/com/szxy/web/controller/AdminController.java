package com.szxy.web.controller;

import com.szxy.pojo.User;
import com.szxy.service.impl.GoodsServiceImpl;
import com.szxy.service.impl.UserServiceImpl;
import com.szxy.utils.UserGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
