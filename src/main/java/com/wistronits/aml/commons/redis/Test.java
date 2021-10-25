package com.wistronits.aml.commons.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 10376
 */
@RestController
@RequestMapping("redis_test")
public class Test {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

/**
 * Redis分布式锁的测试接口
 * 使用jmeter发送并发请求，比如100个并发
 * 在每次jmeter发送请求之前，将redis里面的key值为：redis_share_data的变量值设为0，然后开始发送并发请求，
 * 如果每次请求完成后，键redis_share_data的值为100，则说明锁生效。
 * 因为如果不加锁，那么并发请求同时查询redis的值，并发请求查询到的值有可能是一样的，然后修改，那么最后的值就会不可预期。
 * 如果要验证这个，可以去掉@RedisLock进行测试。我测试过，如果不用@RedisLock，则redis_share_data的值经过并发请求之后的值是错误的，不确定的。
 */
    @RedisLock(key = "rstdi_user_RedisTest")
    @GetMapping("/test")
    public boolean test() {
        //模拟并发的客户端请求对一个共享的变量进行修改
        Object redisData = redisTemplate.opsForValue().get("redis_share_data");
        if (redisData == null) {
            redisData = 0;
        }
        Integer redisInt = Integer.valueOf(String.valueOf(redisData));
        logger.info("实际业务方法中redisInt的值为：{}", redisInt);
        redisInt++;
        redisTemplate.opsForValue().set("redis_share_data", redisInt + "");
        return true;
    }
}
