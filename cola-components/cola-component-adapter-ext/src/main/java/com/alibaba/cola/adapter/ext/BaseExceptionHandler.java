package com.alibaba.cola.adapter.ext;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.BizException;
import com.alibaba.cola.exception.SysException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.net.ConnectException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * Describe: 用于定义异常处理的基类
 * @author qi.wei
 */
public class BaseExceptionHandler {

    public final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * description: 子类可集成相关异常处理 ， 这里只处理通用的相关异常
     * 第三方组件异常，需要自行定于处理：如mybatis的BindingException、spring.tx的DataAccessException
     * @param request
     * @param exception
     * @return
     */
    public Response parseCommonException(HttpServletRequest request, Exception exception) {
        String exceptionName = null;
        if (Objects.nonNull(exception)) {
            exceptionName = exception.getClass().getSimpleName();
        }
        log.error("request uri :{} fail,occur {} error:{}", request.getRequestURI(),exceptionName, exception);
        return SingleResponse.buildFailure(exceptionName, exception.getMessage());
    }

    /**
     * description 相同处理逻辑的异常处理
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler({
            MissingServletRequestParameterException.class,
            NoHandlerFoundException.class,
            IllegalArgumentException.class,
            SQLException.class,
            ConnectException.class,
            SysException.class,
            BizException.class,
            MethodArgumentNotValidException.class,
    })
    @ResponseBody
    public Response handleCommonException(HttpServletRequest request, Exception exception) {
        return parseCommonException(request, exception);
    }

    /**
     * description: 绑定异常
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler({BindException.class})
    @ResponseBody
    public Response badRequest(HttpServletRequest request, BindException exception) {
        StringBuilder errorMessage = new StringBuilder();
        List<FieldError> fieldErrors = exception.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            errorMessage.append(fieldError.getField());
            errorMessage.append("-");
            errorMessage.append(fieldError.getDefaultMessage());
            errorMessage.append(" ");
        }
        log.error("request uri :{} fail,occur BindException error:{}", request.getRequestURI(), exception);
        return SingleResponse.buildFailure("BindException", errorMessage.toString());
    }

    /**
     * description: 兜底的未知全局异常处理
     * @param request
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Response unknownExceptionHandler(HttpServletRequest request, Exception exception) {
        log.error("request uri :{} fail,occur unknown error:{}", request.getRequestURI(), exception);
        return SingleResponse.buildFailure("Exception", exception.getMessage());
    }

}
