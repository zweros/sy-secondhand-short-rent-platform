package com.szxy.service.impl;

import com.szxy.pojo.Notice;
import com.szxy.pojo.Purse;
import com.szxy.pojo.User;
import com.szxy.service.GoodsNoticeService;
import com.szxy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 *  用户
 */
@Service
public class UserServiceImpl {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserService userService;
    @Autowired
    private GoodsNoticeService goodsNoticeService;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Value("${REDIS_TTL_USER_TOKEN_SECONDS}")
    private Long REDIS_TTL_USER_TOKEN_SECONDS;
    @Value("${COOKIE_TTL_TIME_SECONDS}")
    private Integer COOKIE_TTL_TIME_SECONDS;
    @Value("${COOKIE_USER_TOKEN_NAME}")
    private String COOKIE_USER_TOKEN_NAME;


    public Map<String,Object> userRegistryCheck(String phone){
        Map<String,Object> map =
                new HashMap<String,Object>();
        int flag = this.userService.registerCheck(phone);
        if(flag > 0){
            map.put("flag",true);
        } else{
            map.put("flag",false);
        }
        return map;
    }

    public void addUserService(String username,String phone,String password){
        this.userService.userRegisterService(username,phone,password);
    }

    public User userLoginService(String phone, String password,HttpServletResponse resp){
        try {
            User user = this.userService.userLoginService(phone, password);
            if(user != null){
                String token = UUID.randomUUID().toString();
                Cookie cookie = new Cookie(COOKIE_USER_TOKEN_NAME,token);
                cookie.setMaxAge(COOKIE_TTL_TIME_SECONDS);
                cookie.setPath("/");
                resp.addCookie(cookie);
                //更换 redis 序列化器
                this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
                //清空密码
                user.setPassword(null);
                //将 user 数据放入 redis 中
                this.redisTemplate.opsForValue().set(token,user,REDIS_TTL_USER_TOKEN_SECONDS, TimeUnit.SECONDS);
                return user;
            }
        }catch (Exception e){
            logger.debug("Exception -----> [用户登录失败]:可能是 redis 连接失败 ");
            e.printStackTrace();
            return  null;
        }
        return null;
    }

    public Map<String,String> userPasswordCheckService(String phone, String password) {
        User user = this.userService.userLoginService(phone, password);
        Map<String, String> map = new HashMap<>();
        if(user != null){
            map.put("flag","true");
        }else{
            map.put("flag","false");
        }
        return map;
    }

    public void updateUserInfoService(User user,HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(COOKIE_USER_TOKEN_NAME)) {
                    this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
                    User u = (User) this.redisTemplate.opsForValue().get(cookie.getValue());
                    if (u != null) {
                        user.setId(u.getId());
                        //更新用户信息
                        this.userService.updateUserInfoService(user);
                        User cur_user = (User) req.getSession().getAttribute("cur_user");
                        cur_user.setUsername(user.getUsername());
                        cur_user.setQq(user.getQq());
                        req.getSession().setAttribute("cur_user", cur_user);
                        this.redisTemplate.opsForValue().set(cookie.getValue(), cur_user);
                    }
                }
            }
        }
    }

    public void changeNameService(String username, HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if(cookies != null  && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(COOKIE_USER_TOKEN_NAME)) {
                    this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
                    User u = (User) this.redisTemplate.opsForValue().get(cookie.getValue());
                    if(u != null){
                        u.setId(u.getId());
                        u.setUsername(username);
                        //更新用户昵称信息
                        this.userService.updateUserInfoService(u);
                    }
                }
            }
        }
    }

    public void deleteFocusService(Integer goodId) {
        this.userService.deleteFocusGoodsService(goodId);
    }

    public void addNoticeService(String context,HttpServletRequest req) {
        Notice notice = new Notice();
        notice.setContext(context);
        notice.setStatus((byte)0);
        Cookie[] cookies = req.getCookies();
        if(cookies != null  && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(COOKIE_USER_TOKEN_NAME)) {
                    this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
                    User user = (User) this.redisTemplate.opsForValue().get(cookie.getValue());
                    if(user != null){
                        notice.setUser(user);
                        notice.setCreateAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                        this.goodsNoticeService.addNotice(notice);
                    }
                }
            }
        }
    }

    /**
     *  显示我的钱包
     *
     * @param request  HttpServletRequest 对象
     * @param model    Model 对象
     */
    public void showMyPurse(HttpServletRequest request, Model model) {
        User user = this.getUserFromSession(request);
        Purse purse = this.userService.findPurseByUserId(user.getId());
        model.addAttribute("myPurse",purse);
    }

    /**
     * 从 session 中获取用户信息
     *
     * @param request  HttpServletRequest 对象
     * @return
     */
    private User getUserFromSession(HttpServletRequest request){
        return (User)request.getSession().getAttribute("cur_user");
    }
}
