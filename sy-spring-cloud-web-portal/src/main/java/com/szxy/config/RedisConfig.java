package com.szxy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis 配置
 */
@Configuration
public class RedisConfig {

	/**
	 * 1.创建  JedisPoolConfig
	 *
	 * @ConfigurationProperties 读取 application.properties 文件
	 *  会将前缀相同的内容创建一个实体
	 *
	 */
	@Bean
	@ConfigurationProperties(prefix="spring.redis.pool")
	public JedisPoolConfig JedisPoolConfig(){
		JedisPoolConfig config = new JedisPoolConfig();
		return config;
	}

	/****
	 * 2. 创建 JedisConnection
	 */
	@Bean
	@ConfigurationProperties(prefix="spring.redis")
	public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig config){
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setPoolConfig(config);
		return factory;
	}

	/***
	 * 3.创建 RedisTemplate ：用于执行 Redis 操作的方法
	 */
	@Bean
	public RedisTemplate<String,Object> redisTemplate(JedisConnectionFactory factory){
		RedisTemplate<String, Object> template = new RedisTemplate<>();

		//关联 Jedis 连接工厂
		template.setConnectionFactory(factory);
		//为 key 设置序列化器
		template.setKeySerializer(new StringRedisSerializer());
		//为 value 设置序列化器
		template.setValueSerializer(new StringRedisSerializer());

		return template;
	}
}