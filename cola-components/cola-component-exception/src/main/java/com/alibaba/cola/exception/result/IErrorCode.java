package com.alibaba.cola.exception.result;

/**
 * 错误码定义类
 * @author qi.wei
 */
public interface IErrorCode {
  /**
   * 获取异常码
   * @return
   */
  Integer getCode();

  /**
   * 获取异常信息
   * @return
   */
  String getMsg();

  /**
   * 判断是否成功
   * @return
   */
  default boolean success() {
    return (null != getCode() && getCode().equals(ErrorCode.OK.getCode()));
  }

}
