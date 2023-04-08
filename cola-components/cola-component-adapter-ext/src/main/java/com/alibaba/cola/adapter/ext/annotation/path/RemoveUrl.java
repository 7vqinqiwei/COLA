package com.alibaba.cola.adapter.ext.annotation.path;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

/**
 * Describe: 单条删除方法RequestMapping注解与@DeleteUrl方法区分物理删除和逻辑删除
 * @author qi.wei
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping(value = "/remove", method = RequestMethod.GET)
public @interface RemoveUrl {

}
