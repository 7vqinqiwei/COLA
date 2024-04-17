package com.alibaba.cola.biz.exception;

import com.alibaba.cola.exception.BizException;

/**
 * 认证相关异常
 *
 * @author qi.wei
 * @date 2024/4/11 14:46
 */
public class AuthException extends BizException {



    public AuthException() {
        super(AuthException.class.getSimpleName());
    }

    public AuthException(String message, Throwable cause) {
        super(message, cause);
    }
}
