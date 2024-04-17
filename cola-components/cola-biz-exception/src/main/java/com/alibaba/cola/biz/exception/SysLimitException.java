package com.alibaba.cola.biz.exception;

import com.alibaba.cola.exception.BizException;

/**
 * 系统限制异常
 *
 * @author pangu
 */
public class SysLimitException extends BizException {

	private static final long serialVersionUID = 6889855134686307145L;

	public SysLimitException(String message) {
		super(message);
	}
}
