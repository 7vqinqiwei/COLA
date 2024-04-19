package com.alibaba.cola.biz.context;

import java.io.Serializable;

/**
 * 全局多语言上下文
 * @author wayne
 */
public class I18nContext implements Serializable {

    /**
     *
     */
    private final static ThreadLocal<String> THREAD_I18N = new ThreadLocal<>();

    /**
     * TTL 设置多语言内容<br/>
     * @param i18n 多语言
     */
    public static String set(String i18n) {
        THREAD_I18N.set(i18n);
        return i18n;
    }

    /**
     * 获取TTL中的租户ID
     *
     * @return String
     */
    public static String get() {
        return THREAD_I18N.get();
    }

    /**
     * 清除tenantId
     */
    public static void clear() {
        THREAD_I18N.remove();
    }

}
