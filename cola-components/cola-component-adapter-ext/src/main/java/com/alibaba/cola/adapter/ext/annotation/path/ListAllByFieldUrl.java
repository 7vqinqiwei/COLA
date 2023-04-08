package com.alibaba.cola.adapter.ext.annotation.path;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

/**
 * Describe: 列表查询所有数据方法RequestMapping注解 -- 一般处理根据某个字段查询所有匹配的对象
 * @author qqw
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping(value = "/list_all_by_field", method = RequestMethod.GET)
public @interface ListAllByFieldUrl {

}
