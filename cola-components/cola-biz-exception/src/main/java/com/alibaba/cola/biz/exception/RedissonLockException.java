package com.alibaba.cola.biz.exception;

import com.alibaba.cola.exception.BizException;

/**
 * RedissonLock 异常
 *
 * @author qi.wei
 * @date 2024/4/16 23:03
 */
public class RedissonLockException extends BizException {

    public RedissonLockException(String message) {
        super(message);
    }

}
