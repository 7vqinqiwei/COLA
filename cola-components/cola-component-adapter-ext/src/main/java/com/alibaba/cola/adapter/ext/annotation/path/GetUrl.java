package com.alibaba.cola.adapter.ext.annotation.path;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

/**
 * Describe: 查询单条数据方法RequestMapping注解
 * @author qqw
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping(value = "/get", method = RequestMethod.GET)
public @interface GetUrl {
}