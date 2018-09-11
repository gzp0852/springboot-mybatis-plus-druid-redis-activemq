package com.wistronits.aml.commons.exception;

import com.wistronits.aml.commons.util.ResultCode;

/**
 * ResultException
 * @author gzp
 */
public class ResultException extends RuntimeException {

	private ResultCode resultCode;

	/**
	 * ResultException
	 * @param resultCode resultCode
	 */
	public ResultException(ResultCode resultCode) {
		super(resultCode.getMsg());
		this.resultCode = resultCode;
	}

	/**
	 * getResultCode
	 * @return ResultCode
	 */
	public ResultCode getResultCode() {
		return resultCode;
	}
}
