package com.alibaba.cola.pattern.filter;

/**
 * @author shawnzhan.zxy
 * @date 2018/04/17
 */
public interface FilterInvoker<T> {

    /**
     * 调用
     * @param context
     */
    default void invoke(T context){};
}
