package com.alibaba.cola.adapter.ext.annotation.path;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

/**
 * Describe: 单条更新方法RequestMapping注解
 * @author qqw
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping(value = "/update", method = RequestMethod.POST)
public @interface UpdateUrl {
}
