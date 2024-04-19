package com.alibaba.cola.biz.context;

import java.io.Serializable;

/**
 * 租户上下文内容
 *
 * @author qi.wei
 * @date 2023/10/21 0:34
 */
public class TenantContext implements Serializable {

    /**
     * 支持父子线程之间的数据传递
     */
    private final static ThreadLocal<String> THREAD_LOCAL_TENANT = new ThreadLocal<>();

    /**
     * TTL 设置租户ID<br/>
     * <b>谨慎使用此方法,避免嵌套调用。尽量使用 {@code TenantBroker} </b>
     *
     * @param tenantId 租户ID
     */
    public static String set(String tenantId) {
        THREAD_LOCAL_TENANT.set(tenantId);
        return tenantId;
    }

    /**
     * 获取TTL中的租户ID
     *
     * @return String
     */
    public static String get() {
        return THREAD_LOCAL_TENANT.get();
    }

    /**
     * 清除tenantId
     */
    public static void clear() {
        THREAD_LOCAL_TENANT.remove();
    }

}
