package com.wistronits.aml.commons.util;

/**
 * ResultUtils
 * @author gzp
 */
public class ResultUtils {
	/**
	 * ResultUtils
	 * @param resultCode  resultCode
	 * @param msg msg
	 * @param data data
	 * @return resultCode
	 */
	public static Result success(int resultCode, String msg, Object data) {
		Result<Object> result = new Result<>();
		result.setCode(resultCode);
		result.setMsg(msg);
		result.setData(data);
		return result;
	}

	/**
	 * success
	 * @param data data
	 * @return Result
	 */
	public static Result success(Object data) {

		return new Result<>(ResultCode.SUCCESS, data);
	}

	/**
	 * warn
	 * @param resultCode resultCode
	 * @param msg msg
	 * @return Result
	 */
	public static Result warn(ResultCode resultCode, String msg) {
		Result<Object> result = new Result<>(resultCode);
		result.setMsg(msg);
		return result;
	}

	/**
	 * warn
	 * @param resultCode resultCode
	 * @return Result
	 */
	public static Result warn(ResultCode resultCode) {
		return new Result(resultCode);
	}

}
