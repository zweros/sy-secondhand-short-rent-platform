package com.szxy.provider.service;

import com.szxy.pojo.Focus;
import com.szxy.pojo.User;
import com.szxy.utils.UserGrid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//@RequestMapping("/user")
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

    @RequestMapping(value="/addGoodFocus",method = RequestMethod.POST)
    void addGoodsFocusService(@RequestParam("goodId") Integer goodId, @RequestParam("uid")Integer uid);

    @RequestMapping(value="/getUserGoodFocus",method = RequestMethod.POST)
    List<Focus>  findGoodsFocusByUserIdService(@RequestParam("userId") Integer userId);

    @RequestMapping(value="/updateUserInfo",method = RequestMethod.POST)
    void updateUserInfoService(@RequestBody  User user);

    @RequestMapping(value="/deleteFocusGoods",method = RequestMethod.POST)
    void deleteFocusGoodsService(@RequestParam("goodId") Integer goodId);

    @RequestMapping(value="/findUserByPagination",method = RequestMethod.GET)
    UserGrid findUserByPaginationService(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize);
}
