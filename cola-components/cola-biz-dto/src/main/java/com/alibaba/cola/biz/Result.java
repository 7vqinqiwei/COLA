package com.alibaba.cola.biz;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.result.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Objects;

/**
 * 统一响应消息报文
 *
 * @param <T> 　T对象
 * @author pangu
 */
@Data
@Getter
@Schema(description = "统一响应消息报文")
@Slf4j
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> extends Response implements Serializable {

    public static final Result OK = new Result();

    private static final String DEFAULT_SUCCESS_MESSAGE = "处理成功";
    private static final String DEFAULT_FAIL_MESSAGE = "处理失败";
    private static final String DEFAULT_NULL_MESSAGE = "承载数据为空";

    @Schema(description = "状态码", required = true)
    private Integer code;

    @Schema(description = "消息内容", required = true)
    private String msg;

    @Schema(description = "时间戳", required = true)
    private Long time;

    @Schema(description = "业务数据")
    private T data;

    @Schema(description = "错误码,兼容cola,优先code处理", required = false)
    private String errCode;

    @Schema(description = "错误信息,兼容cola,优先msg", required = false)
    private String errMessage;

    public Result() {
        this.code = ErrorCode.OK.getCode();
        this.time = System.currentTimeMillis();
    }

    private Result(ErrorCode resultCode) {
        this(resultCode, null, resultCode.getMsg());
    }

    private Result(ErrorCode resultCode, String msg) {
        this(resultCode, null, msg);
    }

    private Result(ErrorCode resultCode, T data) {
        this(resultCode, data, resultCode.getMsg());
    }

    private Result(ErrorCode resultCode, T data, String msg) {
        this(resultCode.getCode(), data, msg);
    }

    private Result(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.time = System.currentTimeMillis();
    }

    public static <T> Result<T> ok() {
        return data(null);
    }

    /**
     * 返回状态码
     *
     * @param resultCode 状态码
     * @param <T>        泛型标识
     * @return ApiResult
     */
    public static <T> Result<T> success(ErrorCode resultCode) {
        return new Result<>(resultCode);
    }

    public static <T> Result<T> success(String msg) {
        return new Result<>(ErrorCode.OK, msg);
    }

    public static <T> Result<T> success(ErrorCode resultCode, String msg) {
        return new Result<>(resultCode, msg);
    }

    public static <T> Result<T> data(T data) {
        return data(data, DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> Result<T> data(T data, String msg) {
        return data(ErrorCode.OK.getCode(), data, msg);
    }

    public static <T> Result<T> data(int code, T data, String msg) {
        return new Result<>(code, data, data == null ? DEFAULT_NULL_MESSAGE : msg);
    }

    public static <T> Result<T> fail() {
        return new Result<>(ErrorCode.BAD_REQUEST, ErrorCode.BAD_REQUEST.getMsg());
    }

    public static <T> Result<T> fail(String msg) {
        return new Result<>(ErrorCode.BAD_REQUEST, msg);
    }

    public static <T> Result<T> fail(int code, String msg) {
        return new Result<>(code, null, msg);
    }

    public static <T> Result<T> fail(ErrorCode resultCode) {
        return new Result<>(resultCode);
    }

    public static <T> Result<T> fail(ErrorCode resultCode, String msg) {
        return new Result<>(resultCode, msg);
    }

    public static <T> Result<T> condition(boolean flag) {
        return flag ? success(DEFAULT_SUCCESS_MESSAGE) : fail(DEFAULT_FAIL_MESSAGE);
    }

    @Override
    public boolean isSuccess() {
        return Objects.equals(this.getCode() , ErrorCode.OK.getCode());
    }

    /**
     * 转换成response
     */
    public void toResponse() {
        this.errCode = this.code.toString();
        this.errMessage = this.msg;
    }

    public void toResult() {
        this.msg = this.errMessage;
        // errCode是字符串类型，如果转换成Integer不一定成功
        // 这个时候一定要明确两者用法定义是否一致
        // 如果明确原来的Response是成功，那么code就是200
        if (Objects.equals(this.isSuccess(), Boolean.TRUE)) {
            this.code = ErrorCode.OK.getCode();
        }else {
            try {
                this.code = Integer.parseInt(this.errCode);
            } catch (Exception e) {
                log.error("Result.toResult转换异常,请明确两者是否通用,-{}", e);
                this.code = ErrorCode.INTERNAL_SERVER_ERROR.getCode();
            }
        }

    }
}
