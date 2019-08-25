package com.szxy.redis;


import com.szxy.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void setUser() {
        User user = new User();
        user.setUsername("张飞");
        user.setPassword("123");
        //更换 redis 序列化器
        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
        //将 user 数据放入 redis 中
        this.redisTemplate.opsForValue().set("user", user);
    }

}
