package com.wistronits.aml.commons.websocket;

import com.wistronits.aml.chat.entity.User;
import lombok.Data;

import java.util.List;

/**
 * @author gzp
 * @date 2018/9/25 16:41
 */
@Data
public class Messages {
	private Message message;
	private String type;
	private List list;

}
