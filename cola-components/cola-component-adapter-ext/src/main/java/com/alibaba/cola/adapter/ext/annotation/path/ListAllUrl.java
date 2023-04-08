package com.alibaba.cola.adapter.ext.annotation.path;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

/**
 * Describe: 列表查询所有数据方法RequestMapping注解 -- 一般都需要分页处理的 该方法少用于数据较小的清空
 * @author qqw
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping(value = "/list_all", method = RequestMethod.POST)
public @interface ListAllUrl {

}
