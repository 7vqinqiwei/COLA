package com.alibaba.cola.adapter.ext.annotation.path;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

/**
 * Describe: 分页查询方法RequestMapping注解
 * @author qqw
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping(value = "/page", method = RequestMethod.POST)
public @interface PageUrl {
}
