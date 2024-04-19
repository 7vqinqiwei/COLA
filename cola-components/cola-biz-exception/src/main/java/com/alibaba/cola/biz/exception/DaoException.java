 package com.alibaba.cola.biz.exception;


 import com.alibaba.cola.exception.BaseException;
 import com.alibaba.cola.exception.result.ErrorCode;

 /**
  * @author qi.wei
  */
 public class DaoException extends BaseException {
   private static final long serialVersionUID = 5521969295241144310L;

   public DaoException(String message) {
     super(ErrorCode.DAO_EXCEPTION.getCode().toString(), message);
   }

   public DaoException(String message, Throwable cause) {
     super(ErrorCode.DAO_EXCEPTION.getCode().toString(), message, cause);
   }
 }
