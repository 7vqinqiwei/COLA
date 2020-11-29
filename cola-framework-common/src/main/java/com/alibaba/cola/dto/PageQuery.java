package com.alibaba.cola.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * page query
 * <p/>
 *
 * @author Danny.Lee
 * @date 2017/11/1
 */
public abstract class PageQuery extends Query {

    public static final String ASC = "ASC";

    public static final String DESC = "DESC";

    private static final int DEFAULT_PAGE_SIZE = 10;

    private int pageNum = 1;
    private int pageSize = DEFAULT_PAGE_SIZE;
    private boolean needTotalCount = true;
    private List<OrderDesc> orderDescs;
    private String groupBy;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isNeedTotalCount() {
        return needTotalCount;
    }

    public void setNeedTotalCount(boolean needTotalCount) {
        this.needTotalCount = needTotalCount;
    }

    public List<OrderDesc> getOrderDescs() {
        return orderDescs;
    }

    public void addOrderDesc(OrderDesc orderDesc) {
        if (null == orderDescs) {
            orderDescs = new ArrayList<>();
        }
        orderDescs.add(orderDesc);
    }

    public int getOffset() {
        return pageNum > 0 ? (pageNum - 1) * pageSize : 0;
    }

    public String getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }
}
