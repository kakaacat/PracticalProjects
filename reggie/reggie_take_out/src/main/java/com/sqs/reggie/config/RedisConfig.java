package com.sqs.reggie.config;

//使用StringRedisTemplate来代替配置

//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RedisConfig extends CachingConfigurerSupport {
//    @Bean
//    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
//        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
//        //默认的Key序列化器为：JdkSerializationRedisSerializer
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setConnectionFactory(connectionFactory);
//        return redisTemplate;
//    }
//}
