package com.alibaba.cola.adapter.ext.annotation.path;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

/**
 * Describe: 单条保存方法RequestMapping注解 -- 设计原则上应该分add、insert或者update接口区分
 * @author qqw
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping(value = "/save",method = RequestMethod.POST)
public @interface SaveUrl {

}
