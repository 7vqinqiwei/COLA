package com.alibaba.cola.biz.exception;


import com.alibaba.cola.exception.BaseException;
import com.alibaba.cola.exception.result.ErrorCode;

/**
 * 服务异常
 * @author qi.wei
 */
public class ServiceException extends BaseException {
  private static final long serialVersionUID = -4942167468323765290L;

  public ServiceException(String message) {
    super(ErrorCode.SERVICE_EXCEPTION.getCode().toString(), message);
  }

  public ServiceException(String message, Throwable cause) {
    super(ErrorCode.SERVICE_EXCEPTION.getCode().toString(), message, cause);
  }
}
