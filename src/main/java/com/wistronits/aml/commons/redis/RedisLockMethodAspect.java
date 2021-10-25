package com.wistronits.aml.commons.redis;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.UUID;

@Aspect
@Component
public class RedisLockMethodAspect {

    @Autowired
    private RedisUtils redisLockUtil;

    private static final Logger logger = LoggerFactory.getLogger(RedisLockMethodAspect.class);

    @Around("@annotation(com.wistronits.aml.commons.redis.RedisLock)")
    public Object around(ProceedingJoinPoint joinPoint) {
        logger.info("进入Redis分布式锁的AOP方法");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        RedisLock redisLock = method.getAnnotation(RedisLock.class);
        String value = UUID.randomUUID().toString();
        String key = redisLock.key();
        int keyExpire = redisLock.expire() * 1000;
        int tryTimeout = redisLock.waitTime() * 1000;
        logger.info("分布式锁的key:{}，value:{}，expire:{}", key, value,keyExpire);
        try {
            long startTime = System.currentTimeMillis();
            boolean isGetLock = false;
            long usedTime ;
            long repCount = 0;
            //如果没有得到锁，会一直循环尝试获取，直到获得锁或者超时报异常
            while (!isGetLock) {
                //检测是否超时
                long currTime = System.currentTimeMillis();
                usedTime = currTime - startTime;
                if (usedTime >= tryTimeout) {
                    logger.error("获取锁超时");
                    throw new RuntimeException("获取锁超时");
                }
                //获取锁的本质是往Redis写一个有超时时间的key
                isGetLock = redisLockUtil.getLock(key, value, redisLock.expire());
                logger.info("尝试获取锁的结果isLock : {}", isGetLock);
                if (!isGetLock) {
                    repCount++;
                    logger.error("尝试获取锁失败");
                } else {
                    logger.info("获取锁成功，重试次数为：{}，耗时为：{}ms", repCount,usedTime);
                }
            }
            try {
                logger.info("执行实际业务方法===============》");
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                logger.error("Redis锁AOP=========>异常");
                logger.error(throwable.getMessage(), throwable);
                throwable.printStackTrace();
                throw new RuntimeException("执行接口方法异常:" + throwable.getMessage());
            }
        } finally {
            logger.info("finally中释放锁");
            boolean isUnlock = redisLockUtil.releaseLock(key, value);
            if (isUnlock) {
                logger.info("释放锁成功");
            } else {
                logger.error("释放锁失败");
            }
        }
    }
}