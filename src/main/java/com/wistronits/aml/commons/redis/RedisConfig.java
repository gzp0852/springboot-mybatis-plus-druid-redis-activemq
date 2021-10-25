//package com.wistronits.aml.commons.redis;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Import;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
///**
// * date:2019/10/17
// * create by liusongs
// * content:解决spring boot 2.0后jedis模式废弃
// */
//@ConditionalOnClass(com.bsi.core.redis.JedisConnectionFactoryBuilder.class)
//@Import(com.bsi.core.redis.JedisConnectionFactoryBuilder.class)
//public class RedisConfig {
//
//    @Autowired
//    private com.bsi.core.redis.JedisConnectionFactoryBuilder builder;
//
//    @Bean
//    @Primary
//    JedisConnectionFactory jedisConnectionFactory() {
//        return builder.build();
//    }
//
//    @Bean
//    @ConditionalOnBean(value = JedisConnectionFactory.class)
//    RedisTemplate<String, Object> redisTemplate() {
//        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
//        template.setConnectionFactory(jedisConnectionFactory());
//        template.setKeySerializer(new StringRedisSerializer());
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        template.setValueSerializer(new GenericJackson2JsonRedisSerializer(mapper));
//        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer(mapper));
//        template.setHashKeySerializer(new StringRedisSerializer());
//        return template;
//    }
//
//    @Bean
//    @ConditionalOnBean(RedisTemplate.class)
//    RedistUtils redistUtils(@Autowired RedisTemplate redisTemplate) {
//        return new RedistUtils(redisTemplate);
//    }
//}
//
