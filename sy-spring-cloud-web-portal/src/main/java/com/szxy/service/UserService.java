package com.szxy.service;

import com.szxy.pojo.Focus;
import com.szxy.pojo.Purse;
import com.szxy.pojo.User;
import com.szxy.utils.UserGrid;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 用户服务
 */
@FeignClient(name = "sy-spring-clound-zuul-gateway")
public interface UserService {


    @RequestMapping(value = "/user/registerCheck", method = RequestMethod.GET)
    public int registerCheck(@RequestParam("phone") String phone);


    @RequestMapping(value = "/user/register", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    public int userRegisterService(@RequestParam("username") String username,
                                   @RequestParam("phone") String phone,
                                   @RequestParam("password") String password);

    @RequestMapping(value = "/user/login", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    public User userLoginService(@RequestParam("phone") String phone,
                                 @RequestParam("password") String password);

    @RequestMapping(value = "/user/getSellerInfo", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    public User findSellerInfoByIdService(@RequestParam("id") Integer id);

    @RequestMapping(value = "/user/addGoodFocus", method = RequestMethod.POST)
    void addGoodsFocusService(@RequestParam("goodId") Integer goodId, @RequestParam("uid") Integer uid);

    @RequestMapping(value = "/user/getUserGoodFocus", method = RequestMethod.POST)
    List<Focus> findGoodsFocusByUserIdService(@RequestParam("userId") Integer userId);

    @RequestMapping(value = "/user/updateUserInfo", method = RequestMethod.POST)
    void updateUserInfoService(@RequestBody User user);

    @RequestMapping(value = "/user/deleteFocusGoods", method = RequestMethod.POST)
    void deleteFocusGoodsService(@RequestParam("goodId") Integer goodId);

    @RequestMapping(value = "/user/findUserByPagination", method = RequestMethod.GET)
    UserGrid findUserByPaginationService(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize);

    //-------------------- purse
    @RequestMapping(value = "/user/findPurseByUserId", method = RequestMethod.GET)
    Purse findPurseByUserId(@RequestParam("userid") Integer userid);

    @RequestMapping(value = "/user/addPurse", method = RequestMethod.POST)
    void addPurse(@RequestBody  Integer userId);

    @RequestMapping(value = "/user/updatePurseByUserId", method = RequestMethod.POST)
    void updatePurseByUserId(@RequestBody Purse purse);
}
