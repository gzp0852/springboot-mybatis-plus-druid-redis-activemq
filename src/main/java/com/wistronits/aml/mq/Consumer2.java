package com.wistronits.aml.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @author gzp
 * @date 2018/8/29 9:43
 */
@Component
public class Consumer2 {
	@JmsListener(destination = "mytest.queue")
	@SendTo("out.queue")
	public String receiveQueue(String text) {
		System.out.println("Consumer收到的报文为:" + text);
		return "return message" + text;
	}
}
