//package com.wistronits.aml.commons.redis;/*
// * Author: liusongs
// * Date: 2019/10/17 16:22
// * Content:
// */
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import redis.clients.jedis.JedisPoolConfig;
//
//import java.util.Objects;
//
///**
// * date:2019/10/17
// * create by liusongs
// * content:解决spring boot 2.0后jedis模式废弃,从nacos中拉取其对应的配置文件，改动需要在nacos中维护
// */
//@RefreshScope
//@ConfigurationProperties(prefix = "spring.redis")
//public class JedisConnectionFactoryBuilder {
//
//    private Logger logger = LoggerFactory.getLogger(JedisConnectionFactoryBuilder.class);
//
//    private int database = 0;
//    private String url;
//    private String host = "192.168.7.214";
//    private String password;
//    private int port = 6379;
//    private Jedis jedis;
//
//    public static class Jedis {
//        private RedisProperties.Pool pool;
//
//        public RedisProperties.Pool getPool() {
//            return pool;
//        }
//
//        public void setPool(RedisProperties.Pool pool) {
//            this.pool = pool;
//        }
//    }
//
//
//    JedisConnectionFactoryBuilder() {
//    }
//
//    public JedisConnectionFactory build() {
//        JedisClientConfiguration.DefaultJedisClientConfigurationBuilder builder = (JedisClientConfiguration.DefaultJedisClientConfigurationBuilder) JedisClientConfiguration.builder();
//        return new JedisConnectionFactory(redisStandaloneConfiguration(), builder.poolConfig(jedisPoolConfig()).build());
//    }
//
//    private JedisPoolConfig jedisPoolConfig() {
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        if (Objects.isNull(jedis) || Objects.isNull(jedis.pool)) {
//            logger.info("[JedisConnectionFactoryBuilder.jedisPoolConfig 失败，jedis或jedis.pool对象为空，使用默认配置！]");
//            return jedisPoolConfig;
//        }
//        jedisPoolConfig.setMinIdle(this.jedis.pool.getMinIdle());
//        jedisPoolConfig.setMaxIdle(this.jedis.pool.getMaxIdle());
//        jedisPoolConfig.setMaxWaitMillis(this.jedis.pool.getMaxWait().toMillis());
//        jedisPoolConfig.setMaxTotal(this.jedis.pool.getMaxActive());
//        return jedisPoolConfig;
//    }
//
//    private RedisStandaloneConfiguration redisStandaloneConfiguration() {
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//        redisStandaloneConfiguration.setPort(port);
//        redisStandaloneConfiguration.setPassword(password);
//        redisStandaloneConfiguration.setHostName(host);
//        redisStandaloneConfiguration.setDatabase(database);
//        return redisStandaloneConfiguration;
//    }
//
//    public int getDatabase() {
//        return database;
//    }
//
//    public void setDatabase(int database) {
//        this.database = database;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public String getHost() {
//        return host;
//    }
//
//    public void setHost(String host) {
//        this.host = host;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public int getPort() {
//        return port;
//    }
//
//    public void setPort(int port) {
//        this.port = port;
//    }
//
//    public Jedis getJedis() {
//        return jedis;
//    }
//
//    public void setJedis(Jedis jedis) {
//        this.jedis = jedis;
//    }
//}
