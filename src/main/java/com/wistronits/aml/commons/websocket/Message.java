package com.wistronits.aml.commons.websocket;

import com.wistronits.aml.chat.entity.User;
import lombok.Data;

/**
 * @author gzp
 * @date 2018/9/25 16:09
 */
@Data
public class Message {
	private String content;
	private String from;
	private String to;
	private String time;
	private User user;
}
