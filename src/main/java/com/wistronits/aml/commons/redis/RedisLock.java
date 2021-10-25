package com.wistronits.aml.commons.redis;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 用Redis实现的分布式锁。
 * 原理是在Redis设置一个有过期时间的key，用Lua脚本操作Redis，保证原子性
 * 如何使用：
 * （1）在需要加锁的方法上面加上@RedisLock；
 * （2）key值必须输入，最好设为比较好辨识的全局唯一的字符串，比如rstdi_user_page。微服务名+类映射+方法映射
 * （3）其余3个参数选填
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RedisLock {

    /**
     * 必填。分布式业务key，必须唯一，全微服务，全业务，全集群必须唯一
     * 最好设为比较好辨识的全局唯一的字符串
     *
     *
     */
    String key();

    /**
     * 锁的过期时间
     * 单位秒，默认是60
     */
    int expire() default 60;

    /**
     * 尝试加锁，最多等待时间
     * 单位秒，默认30
     */
    int waitTime() default 30;

    /**
     * 锁的超时时间单位
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}