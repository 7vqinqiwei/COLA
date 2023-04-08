package com.alibaba.cola.adapter.ext.annotation.path;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

/**
 * Describe: 单条删除方法RequestMapping注解
 * @author qi.wei
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping(value = "/delete", method = RequestMethod.GET)
public @interface DeleteUrl {

}
