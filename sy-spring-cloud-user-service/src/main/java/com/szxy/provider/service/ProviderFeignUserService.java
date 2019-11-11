package com.szxy.provider.service;

import com.szxy.pojo.Focus;
import com.szxy.pojo.Purse;
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

    @RequestMapping(value="/registerCheck",method = RequestMethod.GET)
    public int registerCheck(@RequestParam("phone") String phone);

    @RequestMapping(value="/register",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    public int userRegisterService(@RequestParam("username") String username,
                             @RequestParam("phone")  String phone,
                             @RequestParam("password") String password);

    @RequestMapping(value="/login",method = RequestMethod.POST,
                        consumes = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    public User userLoginService(@RequestParam("phone")String phone,
                                    @RequestParam("password")String password);

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

    @RequestMapping(value="/addUser",method = RequestMethod.POST)
    void addUserService(@RequestBody User user);

    @RequestMapping(value="/findUserByphone",method = RequestMethod.POST)
    User findUserByPhoneService(@RequestParam("phone") String phone);

    @RequestMapping(value="/findPurseByUserId", method = RequestMethod.GET)
    Purse findPurseByUserId(@RequestParam("userid") Integer userid);

    @RequestMapping(value = "/addPurse", method = RequestMethod.POST)
    void addPurse(@RequestBody  Integer userId);

    @RequestMapping(value = "/updatePurseByUserId", method = RequestMethod.POST)
    void updatePurseByUserId(@RequestBody Purse purse);

}
