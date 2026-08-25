package com.alibaba.cola.catchlog;

public interface ResponseHandlerI {
    Object handle(Class<?> returnType, String errCode, String errMsg);
}
