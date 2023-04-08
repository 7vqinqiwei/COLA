package com.alibaba.cola.adapter.ext;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.BaseException;
import com.alibaba.cola.exception.result.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Describe: 用于定义controller的基类
 * @author qi.wei
 */
public abstract class BaseController {

    public final Logger log = LoggerFactory.getLogger(getClass());

    public <T> Response success(T data) {
        return SingleResponse.of(data);
    }

    public SingleResponse failed(ErrorCode errorCode) {
        return SingleResponse.buildFailure(errorCode.getCode().toString(), errorCode.getMsg());
    }

    public SingleResponse failed(Integer code, String msg) {
        return SingleResponse.buildFailure(code.toString(), msg);
    }

    public SingleResponse failed(BaseException e) {
        return SingleResponse.buildFailure(e.getErrCode(), e.getMessage());
    }


}
