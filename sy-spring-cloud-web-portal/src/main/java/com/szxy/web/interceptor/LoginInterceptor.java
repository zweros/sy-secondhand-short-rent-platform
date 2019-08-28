package com.szxy.web.interceptor;

import com.szxy.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther:zwer
 * @Date:2019/8/28 17:06
 * @Description:com.szxy.web.interceptor
 * @Version:1.0
 **/
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Value("${COOKIE_USER_TOKEN_NAME}")
    private String COOKIE_USER_TOKEN_NAME;
    @Value("${REDIS_TTL_USER_TOKEN_SECONDS}")
    private Long REDIS_TTL_USER_TOKEN_SECONDS;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(COOKIE_USER_TOKEN_NAME)) {
                    this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
                    User u = (User) this.redisTemplate.opsForValue().get(cookie.getValue());
                    if(u != null){
                        if(httpServletRequest.getSession().getAttribute("cur_user") != null){
                            this.redisTemplate.opsForValue().set(cookie.getValue(),u,REDIS_TTL_USER_TOKEN_SECONDS);
                            return true; //用户已登录，放行
                        }
                    }
                }
            }
        }
        httpServletRequest.getSession().invalidate();//清除 session 信息
        httpServletResponse.sendRedirect("/error/noLogin");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
