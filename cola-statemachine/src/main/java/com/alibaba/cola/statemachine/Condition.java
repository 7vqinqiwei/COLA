package com.alibaba.cola.statemachine;

/**
 * Condition
 *
 * @author Frank Zhang
 * @date 2020-02-07 2:50 PM
 */
public interface Condition<C> {

    /** 上下文context satisfied
     * @param context context object
     * @return whether the context satisfied current condition
     */
    boolean isSatisfied(C context);

    /**
     * 获取当前Condition的名称
     * @return
     */
    default String name(){
        return this.getClass().getSimpleName();
    }
}