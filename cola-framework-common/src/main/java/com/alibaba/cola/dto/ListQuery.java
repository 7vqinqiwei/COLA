package com.alibaba.cola.dto;

import java.util.List;

/**
 * list query
 * @author qqw
 */
public abstract class ListQuery extends Query {

    private List<OrderDesc> orderDescs;

    public List<OrderDesc> getOrderDescs() {
        return orderDescs;
    }

    public void setOrderDescs(List<OrderDesc> orderDescs) {
        this.orderDescs = orderDescs;
    }
}
