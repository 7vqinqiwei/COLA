package com.alibaba.cola.exception.result;

/**
 * 错误类
 * @author qi.wei
 */
public enum ErrorCode implements IErrorCode {
  FAILED(Integer.valueOf(0), "失败"),
  OK(Integer.valueOf(200), "成功"),
  MOVED_PERMANENTLY(Integer.valueOf(301), "请求的资源已经永久转移"),
  FOUND(Integer.valueOf(302), "请重新发送请求"),
  BAD_REQUEST(Integer.valueOf(400), "请求错误，请修正请求"),
  UNAUTHORIZED(Integer.valueOf(401), "没有被授权或者授权已经失效"),
  FORBIDDEN(Integer.valueOf(403), "请求被理解，但是拒绝执行"),
  NOT_FOUND(Integer.valueOf(404), "访问的资源不存在"),
  METHOD_NOT_ALLOWED(Integer.valueOf(405), "请求方法不允许被执行"),
  NOT_ACCEPTABLE(Integer.valueOf(406), "请求的资源不满足请求者要求"),
  REQUEST_TIMEOUT(Integer.valueOf(408), "请求超时"),
  GONE(Integer.valueOf(410), "请求的资源不可用"),
  TOO_MANY_REQUESTS(Integer.valueOf(429), "请求过多"),
  INTERNAL_SERVER_ERROR(Integer.valueOf(500), "服务器内部错误"),
  BAD_GATEWAY(Integer.valueOf(502), "响应无效"),
  SERVICE_UNAVAILABLE(Integer.valueOf(503), "服务器维护或者过载，拒绝服务"),
  GATEWAY_TIMEOUT(Integer.valueOf(504), "上游服务器超时"),
  HTTP_VERSION_NOT_SUPPORTED(Integer.valueOf(505), "HTTP协议版本不支持"),
  DAO_EXCEPTION(Integer.valueOf(600), "数据访问层异常"),
  SERVICE_EXCEPTION(Integer.valueOf(601), "服务层异常"),
  INVALID_PARAMETERS(Integer.valueOf(602), "无效的参数");

  private Integer code;

  private String msg;

  @Override
  public Integer getCode() {
    return this.code;
  }

  @Override
  public String getMsg() {
    return this.msg;
  }

  ErrorCode(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }
}
