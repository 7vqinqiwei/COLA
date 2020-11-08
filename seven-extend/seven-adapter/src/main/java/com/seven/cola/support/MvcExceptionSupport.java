package com.seven.cola.support;

import com.alibaba.cola.dto.ErrorCodeI;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.BizException;
import com.alibaba.cola.exception.SysException;
import com.alibaba.cola.exception.framework.BasicErrorCode;
import com.alibaba.cola.exception.framework.ColaException;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Optional;

/**
 * @author carter
 * create_date  2020/6/15 18:19
 * description     统一的mvc层的异常处理
 */
@RestControllerAdvice
@Slf4j
public class MvcExceptionSupport implements WebMvcConfigurer {

    @Value("${spring.mvc.showExceptionData:false}")
    private Boolean showData;

    /**
     * 处理框架异常
     * @param exception 框架异常
     * @return
     */
    @ExceptionHandler(ColaException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public SingleResponse handleColaException(ColaException exception) {

        SingleResponse response = new SingleResponse();
        response.setSuccess(false);
        response.setErrCode(BasicErrorCode.COLA_ERROR.getErrCode());


        String errMessage = Optional.ofNullable(exception.getMessage())
                .filter(StringUtils::isNotBlank)
                .filter(StringUtils::isAlpha)
                .orElse(BasicErrorCode.COLA_ERROR.getErrDesc());
        response.setErrMessage(errMessage);
        response.setCorrectGuid(BasicErrorCode.COLA_ERROR.getCorrectGuid());

        if (showData) {
            response.setData(Throwables.getStackTraceAsString(exception));
        }
        return response;
    }

    /**
     * 处理系统异常
     *
     * @param exception 异常
     * @return
     */
    @ExceptionHandler(SysException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public SingleResponse handleSysException(SysException exception) {
        ErrorCodeI errorCodeI = exception.getErrCode();
        return getSingleResponseFromErrorCode(errorCodeI, exception);
    }


    /**
     * 处理业务异常
     *
     * @param exception 业务异常
     * @return
     */
    @ExceptionHandler(BizException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public SingleResponse handleBizException(BizException exception) {
        ErrorCodeI errorCodeI = exception.getErrCode();
        return getSingleResponseFromErrorCode(errorCodeI, exception);
    }

    private SingleResponse getSingleResponseFromErrorCode(ErrorCodeI errorCodeI, Exception exception) {
        SingleResponse response = new SingleResponse();
        response.setSuccess(false);
        response.setErrCode(errorCodeI.getErrCode());
        response.setErrMessage(errorCodeI.getErrDesc());
        response.setCorrectGuid(errorCodeI.getCorrectGuid());
        if (showData) {
            response.setData(errorCodeI.getServiceName() + "|" + Throwables.getStackTraceAsString(exception));
        }
        return response;
    }

    /**
     * 处理参数校验异常
     *
     * @param exception 校验异常 支持3种方式
     * @return
     */
    @ExceptionHandler(value = {IllegalArgumentException.class, MethodArgumentNotValidException.class, BindException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SingleResponse handleParamException(Exception exception) {

        SingleResponse response = new SingleResponse();
        response.setSuccess(false);



        if (exception instanceof IllegalArgumentException) {
            //如果是非法参数异常，也归类为校验参数类异常
            response.setErrCode(BasicErrorCode.VALID_ERROR.getErrCode());
            String errMessage = Optional.ofNullable(exception.getMessage())
                    .filter(StringUtils::isNotBlank)
                    .filter(StringUtils::isAlpha)
                    .orElse(BasicErrorCode.VALID_ERROR.getErrDesc());
            response.setErrMessage(errMessage);
            response.setCorrectGuid(BasicErrorCode.VALID_ERROR.getCorrectGuid());

            if (showData) {
                response.setData(Throwables.getStackTraceAsString(exception));
            }
            return response;
        }

        if (exception instanceof MethodArgumentNotValidException) {
            //如果是spring框架的校验异常，这里做特殊处理，当成提示信息包装给到前端
            response.setErrCode(BasicErrorCode.VALID_ERROR.getErrCode());
            StringBuffer stringBuffer = new StringBuffer("");
            StringBuffer errKeyBuffer = new StringBuffer("");
            List<FieldError> fieldErrors = ((MethodArgumentNotValidException) exception).getBindingResult().getFieldErrors();
            fieldErrors.forEach(err -> {
                stringBuffer.append(err.getObjectName())
                            .append(".").append(err.getField()).append("-").
                                    append(err.getDefaultMessage()).append(",");
                errKeyBuffer.append(err.getField()).append("=").append("");

            });

            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            response.setErrMessage(stringBuffer.toString());
            response.setErrKey(errKeyBuffer.toString());

            if (showData) {
                response.setData(Throwables.getStackTraceAsString(exception));
            }
            return response;
        }

        if (exception instanceof BindException) {
            response.setErrCode(BasicErrorCode.VALID_ERROR.getErrCode());
            StringBuffer stringBuffer = new StringBuffer("");
            StringBuffer errKeyBuffer = new StringBuffer("");
            List<FieldError> fieldErrors = ((BindException) exception).getBindingResult().getFieldErrors();
            fieldErrors.forEach(err -> {
                stringBuffer.append(err.getObjectName())
                        .append(".").append(err.getField()).append("-").
                        append(err.getDefaultMessage()).append(",");
                errKeyBuffer.append(err.getField()).append("=").append("");
            });

            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            response.setErrMessage(stringBuffer.toString());
            response.setErrKey(errKeyBuffer.toString());

            if (showData) {
                response.setData(Throwables.getStackTraceAsString(exception));
            }
            return response;
        }
        return response;
    }


    /**
     * 处理404异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public SingleResponse handle404Exception(NoHandlerFoundException exception) {

        SingleResponse singleResponse = new SingleResponse();
        singleResponse.setSuccess(false);
        singleResponse.setErrCode(BasicErrorCode._404_ERROR.getErrCode());
        singleResponse.setErrMessage(BasicErrorCode._404_ERROR.getErrDesc());
        singleResponse.setCorrectGuid(BasicErrorCode._404_ERROR.getCorrectGuid());
        singleResponse.setData(exception.getHttpMethod() + " " + exception.getRequestURL() + "资源不存在");
        return singleResponse;
    }

    /**
     * 托底异常处理
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public SingleResponse handleException(Exception exception) {

        Throwable throwable = exception.getCause();
        if (throwable instanceof BizException){
            BizException bizException = (BizException) throwable;
            ErrorCodeI errorCodeI = bizException.getErrCode();
            String errCode = bizException.getErrCode().getErrCode();
            String msg = bizException.getMessage();
            if (StringUtils.isEmpty(msg)){
                msg = errorCodeI.getErrDesc();
            }
            return SingleResponse.buildFailure(errCode,msg);
        }
        //托底的异常处理
        SingleResponse response = new SingleResponse();
        response.setErrCode(BasicErrorCode.SYS_ERROR.getErrCode());
        String errMessage = Optional.ofNullable(exception.getMessage())
                .filter(StringUtils::isNotBlank)
                .filter(StringUtils::isAlpha)
                .orElse(BasicErrorCode.SYS_ERROR.getErrDesc());
        response.setErrMessage(errMessage);
        response.setCorrectGuid(BasicErrorCode.SYS_ERROR.getCorrectGuid());
        response.setSuccess(false);

        if (showData) {
            response.setData(Throwables.getStackTraceAsString(exception));
        }
        log.info("请求异常信息-{}",exception);
        return response;

    }


}