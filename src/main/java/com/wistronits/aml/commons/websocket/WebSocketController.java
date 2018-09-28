package com.wistronits.aml.commons.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 10376
 * 2018/3/19
 */
@RestController
public class WebSocketController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SimpMessagingTemplate template;

    /**
     * 发送websocket消息
     *
     * @param bean WebSocketSendBean
     */
    @PostMapping("/sendWebSocket")
    public void send(@RequestBody WebSocketSendBean bean) {
        try {
            logger.info("发送websocket：" + bean.toString());
            template.convertAndSend(bean.getPath(), bean.getMsg());
        } catch (Exception e) {
            logger.info("消息发送失败！");
            e.printStackTrace();
        }
    }

      /**
     * heartBeat
     *
     * @param token Map
     */
    @MessageMapping("/heartbeat")
    public void heartBeat(Map token) {
        template.convertAndSend("/itomTopic/" + token.get("token"), "ok");
    }

    @Autowired
    private WebSocketProduct ws;
    @RequestMapping("/Welcome1")
    @ResponseBody
    public String sendMessage() throws Exception{
        ws.sendMessage();
        return "is ok";
    }

    @MessageMapping("/welcome")
    @ResponseBody
    public void welcome(String name) throws Exception{
        logger.error(name);
    }
}

