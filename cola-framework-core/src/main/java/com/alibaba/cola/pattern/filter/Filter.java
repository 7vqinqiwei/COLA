package com.alibaba.cola.pattern.filter;

/**
 *
 * @author seven
 */
public interface Filter<T> {
	/**
	 * 过滤器
	 * @param context
	 * @param nextFilter
	 */
	void doFilter(T context, FilterInvoker nextFilter);

}