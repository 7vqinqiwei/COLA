package com.alibaba.cola.adapter.ext.validator;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * 使用spring validation校验配置，同时将messageSource注入支持i18n国际化处理
 * @author qi.wei
 */
@Configuration
public class ValidatorConfiguration implements WebMvcConfigurer {

    @Resource
    private MessageSource messageSource;


    @Override
    public Validator getValidator() {
        return validator();
    }
    @Bean
    public Validator validator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource);
        return validator;
    }

}
