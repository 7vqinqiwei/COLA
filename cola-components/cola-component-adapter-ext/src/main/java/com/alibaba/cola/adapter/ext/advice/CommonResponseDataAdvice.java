package com.alibaba.cola.adapter.ext.advice;

import com.alibaba.cola.adapter.ext.annotation.advice.IgnoreResponseAdvice;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Collection;
import java.util.Objects;

/**
 * <h1>实现统一响应</h1>
 *
 * @author qi.wei
 * @RestControllerAdvice(value = "pacakgename**")
 */
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {

    /**
     * <h2>判断是否需要对响应进行处理</h2>
     */
    @Override
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> aClass) {

        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        if (methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {

        // 定义最终的返回对象
        Response response;
        if (Objects.isNull(o)) {
            return SingleResponse.buildSuccess();
        }
        if (o instanceof Collection) {
            response = MultiResponse.of((Collection)o);
        } else if (o instanceof Response) {
            return o;
        } else {
            return SingleResponse.of(o);
        }
        return response;
    }
}
