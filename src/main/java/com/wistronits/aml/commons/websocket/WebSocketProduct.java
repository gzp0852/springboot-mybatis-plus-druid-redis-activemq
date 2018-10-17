package com.wistronits.aml.commons.websocket;

import com.alibaba.fastjson.JSON;
import com.wistronits.aml.chat.service.IUserService;
import com.wistronits.aml.commons.Constant;
import com.wistronits.aml.commons.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * @author 10376
 * 2018/3/19
 */
@Component
public class WebSocketProduct {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IUserService iUserService;
    /**
     * 发送websocket消息
     *
     * @param bean WebSocketSendBean
     */
    public void send(WebSocketSendBean bean) {
        try {
            logger.info("发送websocket：" + bean.toString());
            template.convertAndSend(bean.getPath(), bean.getMsg());
        } catch (Exception e) {
            logger.info("消息发送失败！");
            e.printStackTrace();
        }
    }

    /**
     * 用户被强制下线实时监控
     */
    public void offLine(String token) {
        try {
            // 前缀
            String pre = "/chatTopic/user/";
            String path = pre + token;
            String message = "您已在另一台机器上登录！";
            WebSocketSendBean bean = new WebSocketSendBean(path, message);
            send(bean);
        } catch (Exception e) {
            logger.error("强制下线信息推送失败");
        }
    }

    /**
     * 用户超时下线实时监控
     */
    public void userTimeOut(String token) {

        try {
            // 前缀
            String pre = "/chatTopic/user/";
            String path = pre + token;
            String message = "您已超时，请重新登录！";
            WebSocketSendBean bean = new WebSocketSendBean(path, message);
            send(bean);
        } catch (Exception e) {
            logger.error("超时下线信息推送失败！");
        }
    }


    public void sendMessage() throws Exception{
        for(int i=0;i<10;i++)
        {
            Thread.sleep(1500);
            String path = "/chatTopic/getResponse";
            String message = "nichaoshi";
            WebSocketSendBean bean = new WebSocketSendBean(path, JSON.toJSONString(message));
            send(bean);
            System.out.println("----------------------send"+i);
        }
    }

    public void chatAll(String msg) {
        try {
            // 前缀
            String path = "/chatTopic/users/";
            WebSocketSendBean bean = new WebSocketSendBean(path, msg);
            send(bean);
        } catch (Exception e) {
            logger.error("发送所有人推送失败");
        }
    }
}
