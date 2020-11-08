package com.seven.cola.support;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @author qinqw
 */
@Component
@Slf4j
public class ReWriteDefaultErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttrs = super.getErrorAttributes(webRequest, options);
        errorAttrs.put("success",false);
        return errorAttrs;
    }
}
