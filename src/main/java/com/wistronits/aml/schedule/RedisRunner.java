package com.wistronits.aml.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author 10376
 */
@Component
public class RedisRunner implements ApplicationRunner {

    @Autowired
    private TimeOutOfflineTask timeOutOfflineTask;

    @Override
    public void run(ApplicationArguments args){
        //开启超时下线检测icon
        timeOutOfflineTask.start("0/30 * * * * ?");
    }
}