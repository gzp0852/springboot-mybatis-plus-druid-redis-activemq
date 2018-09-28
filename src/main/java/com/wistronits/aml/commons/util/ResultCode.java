package com.wistronits.aml.commons.util;

/**
 * ResultCode
 * @author gzp
 */
public enum ResultCode {

	/**
	 * 请求成功
	 */
	SUCCESS(0, "请求成功"),

	/**
	 * 请求失败
	 */
	WEAK_NET_WORK(-1, "网络异常，请稍后重试"),
	LOGIN_ERROR(10001,"用户名或密码错误"),
	FAIL(20101, "名称已存在"),
	CodeFAIL(20102, "编码已存在"),
	DELETE_ERROR(20100, "删除失败"),
	PARAMETER_ERROR(20105,"参数错误");


	/**
	 * 请求代码
	 */
	private int code;
	private String msg;

	ResultCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}