package com.alibaba.cola.adapter.ext.annotation.path;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

/**
 * Describe: 批量删除方法RequestMapping注解与@BatchDeleteUrl区分物理和逻辑
 * @author qi.wei
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping(value = "/batch_remove", method = RequestMethod.POST)
public @interface BatchRemoveUrl {

}
