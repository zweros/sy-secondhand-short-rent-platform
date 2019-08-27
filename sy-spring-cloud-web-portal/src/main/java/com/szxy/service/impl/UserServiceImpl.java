package com.szxy.service.impl;

import com.szxy.pojo.User;
import com.szxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 *  用户
 */
@Service
public class UserServiceImpl {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Value("${TTL_USER_TOKEN_DAYS}")
    private Long TTL_USER_TOKEN_DAYS;

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
        User user = this.userService.userLoginService(phone, password);
        if(user != null){
            String token = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("token",token);
            cookie.setMaxAge(60*60*7);
            cookie.setPath("/");
            resp.addCookie(cookie);
            //更换 redis 序列化器
            this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
            //清空密码
            user.setPassword(null);
            //将 user 数据放入 redis 中
            this.redisTemplate.opsForValue().set(token,user,TTL_USER_TOKEN_DAYS, TimeUnit.DAYS);
            return user;
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
}
