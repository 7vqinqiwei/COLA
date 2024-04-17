package com.alibaba.cola.biz.exception;

import com.alibaba.cola.exception.BizException;

/**
 * 验证码异常
 *
 * @author qi.wei
 * @date 2024/4/16 23:02
 */
public class CaptchaException extends BizException {

    public CaptchaException(String message) {
        super(message);
    }

}
