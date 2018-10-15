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
	PARAMETER_ERROR(20105,"参数错误"),
	IMG_NOT_EMPTY(30101, "图片不存在"),
	IMG_FORMAT_ERROR(30102, "图片类型错误"),
	SAVE_IMG_ERROE(30103, "保存图片失败"),
	PASSWORD_NULL(10101, "密码不能为空"),
	PASSWORD_ERR_OLD(10103, "原密码错误"),
	PASSWORD_SAME(10102, "与原密码相同");



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