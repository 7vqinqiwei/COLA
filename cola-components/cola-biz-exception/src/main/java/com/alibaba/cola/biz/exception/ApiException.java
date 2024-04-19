package com.alibaba.cola.biz.exception;

/**
 * 调用接口异常信息
 * @author qi.wei
 */
public class ApiException extends RuntimeException {
  private static final long serialVersionUID = -7671976010241287497L;

  private int errorCode;

  public ApiException(int code, String msg) {
    super(msg);
    this.errorCode = code;
  }

  public ApiException(int code, String msg, Throwable cause) {
    super(code + ":" + msg, cause);
    this.errorCode = code;
  }

  public int getErrorCode() {
    return this.errorCode;
  }
}
