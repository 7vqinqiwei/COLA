package com.alibaba.cola.biz.context;

import java.io.Serializable;

/**
 * 用户上下文
 *  需要存放的用户对象是未知的
 * @author qi.wei
 * @date 2023/10/21 0:35
 */
public class UserContext<T> implements Serializable {

    /**
     * 用户上下文持有者
     */
    private final static ThreadLocal USER_HOLDER = new ThreadLocal<>();

    /**
     * 只设置用户线程不改变用户对象
     * @param loginUser
     * @param <T>
     * @return
     */
    public static <T> T set(T loginUser) {
        USER_HOLDER.set(loginUser);
        return loginUser;
    }

    /**
     * 获取用户上下文
     * @param <T>
     * @return
     */
    public static <T> T get() {
        return (T) USER_HOLDER.get();
    }

    /**
     * 清除tenantId
     */
    public static void clear() {
        USER_HOLDER.remove();
    }

}
