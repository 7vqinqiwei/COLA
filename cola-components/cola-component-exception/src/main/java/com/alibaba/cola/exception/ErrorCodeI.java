package com.alibaba.cola.exception;

/**
 * Extends your error codes in your App by implements this Interface.
 * @author fulan.zjf
 * @date 2017/12/18
 */
public interface ErrorCodeI {

    /**
     * 获取错误编码
     * @return
     */
    String getErrCode();

    /**
     * 获取错误描述
     * @return
     */
    String getErrDesc();

}
