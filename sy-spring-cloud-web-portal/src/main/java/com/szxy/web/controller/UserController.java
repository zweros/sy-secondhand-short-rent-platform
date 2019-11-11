package com.szxy.web.controller;

import com.szxy.pojo.*;
import com.szxy.service.impl.GoodsServiceImpl;
import com.szxy.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  用户 Controller
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private GoodsServiceImpl goodsService;
    @Value("${SESSION_MAX_INACTIVE_INTERVAL_SECONDS}")
    private Integer SESSION_MAX_INACTIVE_INTERVAL_SECONDS;
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
    public String userLogin(String phone, String password,
                            HttpSession session,HttpServletResponse resp){
        User user = this.userService.userLoginService(phone, password, resp);
        if(user != null){
            // TODO: 2019/8/25 暂时存放在 session 中
            user.setPassword(null);
            session.setAttribute("cur_user",user);
            //设置 session 有效期
            session.setMaxInactiveInterval(SESSION_MAX_INACTIVE_INTERVAL_SECONDS);
            logger.info("user:登录用户："+user);
        }
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

    /**
     * 显示用户主页
     * @return
     */
    @RequestMapping(value="/home",method= RequestMethod.GET)
    public String userHome(@RequestParam(defaultValue = "1",required = false) Integer pageNum,
                           @RequestParam(defaultValue = "5",required = false) Integer pageSize,
                           Model model){
       //ist<Notice> noticeList = this.goodsService.findAllNoticeService();
        NoticeGrid noticeGrid = this.goodsService.findNoticeByPaginationService(pageNum,pageSize);
        model.addAttribute("noticeGrid",noticeGrid);
        return "/user/home";
    }

    /**
     * 显示用户已发布的物品
     * @return
     */
    @RequestMapping(value="/allGoods",method= RequestMethod.GET)
    public String userPublishedAllGoods(HttpServletRequest req, Model model){
        List<GoodsExtend> goodsExtendList = this.goodsService.findUserPublishedAllGoods(req);
        model.addAttribute("goodsExtendList",goodsExtendList);
        return "/user/goods";
    }

    /**
     * 个人设置
     * @return
     */
    @RequestMapping(value="/basic",method= RequestMethod.GET)
    public String userBasic(Model model){
        return "/user/basic";
    }

    /**
     * 添加关用户注物品
     * @return
     */
    @RequestMapping(value="/addFocus/{goodId}",method= RequestMethod.GET)
    public String addUserAllFocus(@PathVariable Integer goodId,HttpServletRequest req){
        this.goodsService.addGoodFocusService(goodId,req);
        return "redirect:/user/allFocus";
    }

    /**
     * 查找用户关注物品
     * @param req
     * @param model
     * @return
     */
    @RequestMapping(value="/allFocus",method= RequestMethod.GET)
    public String findUserAllFocus(HttpServletRequest req,Model model){
            List<GoodsExtend> goodsExtendList = this.goodsService.findGoodFocusService(req);
            model.addAttribute("goodsExtendList",goodsExtendList);
            return "/user/focus";
    }

    /**
     * 删除用户关注的物品
     * @param goodId
     * @return
     */
    @RequestMapping(value="/deleteFocus/{goodId}",method= RequestMethod.GET)
    public String deleteFocus(@PathVariable Integer goodId){
            this.userService.deleteFocusService(goodId);
            return "redirect:/user/allFocus";
    }

    /**
     * 修改用户个人信息
     */
    @RequestMapping(value="/updateInfo",method= RequestMethod.POST)
    public String userupdateInfo(User user,HttpServletRequest req){
        this.userService.updateUserInfoService(user,req);
        return  "redirect:/user/home";
    }

    /**
     * 修改用户昵称
     * @param username
     * @param req
     * @return
     */
    @RequestMapping(value="/changeName",method= RequestMethod.POST)
    public String changeName(String username,HttpServletRequest req){
        this.userService.changeNameService(username,req);
        return  "redirect:/user/home";
    }

    /**
     * 添加求购信息
     * @param context
     * @param req
     * @return
     */
    @RequestMapping(value="/insertSelective",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,String> insertSelective(String context,HttpServletRequest req){
        this.userService.addNoticeService(context,req);
        HashMap<String, String> map = new HashMap<>();
        map.put("msg","ok");
        return map;
    }

    /**
     * 显示我的钱包信息
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="/myPurse",method= RequestMethod.GET)
    public String showMyPurse(HttpServletRequest request, Model model){
        this.userService.showMyPurse(request, model);
        return "/user/purse";  // 跳转到我的钱包页面中
    }

}
