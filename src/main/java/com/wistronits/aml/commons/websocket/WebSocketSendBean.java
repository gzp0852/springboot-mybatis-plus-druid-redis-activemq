package com.wistronits.aml.commons.websocket;

import java.util.Objects;

/**
 * WebSocketSendBean
 *
 * @author 10376 2018/3/19
 */
public class WebSocketSendBean {
	/**
	 * 消息发送目的地
	 */
	private String path;

	/**
	 * 消息发送内容
	 */
	private String msg;

	public WebSocketSendBean(String path, String message) {
		this.path = path;
		this.msg = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {

			return true;
		}
		if (o == null || getClass() != o.getClass()) {

			return false;
		}
		WebSocketSendBean that = (WebSocketSendBean) o;
		return Objects.equals(path, that.path) && Objects.equals(msg, that.msg);
	}

	@Override
	public int hashCode() {

		return Objects.hash(path, msg);
	}

	@Override
	public String toString() {
		return "WebSocketSendBean{" + "path='" + path + '\'' + ", msg='" + msg + '\'' + '}';
	}
}
