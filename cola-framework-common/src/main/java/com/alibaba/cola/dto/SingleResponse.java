package com.alibaba.cola.dto;

/**
 * Response with single record to return
 * <p/>
 *
 * @author Danny.Lee
 * @date 2017/11/1
 */

public class SingleResponse<T> extends Response {

    public static final String DEFAULT_FAIL_CODE = "DEFAULT_FAIL_CODE";

    private T data;

    public static <T> SingleResponse<T> of(T data) {
        SingleResponse<T> singleResponse = new SingleResponse<>();
        singleResponse.setSuccess(true);
        singleResponse.setData(data);
        return singleResponse;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static SingleResponse buildFailure(String errCode, String errMessage) {
        SingleResponse response = new SingleResponse();
        response.setSuccess(false);
        response.setErrCode(errCode);
        response.setErrMessage(errMessage);
        return response;
    }

    public static <T> SingleResponse<T> data(T data) {
        return of(data);
    }

    public static SingleResponse buildFailure() {
        return buildFailure("系统异常");
    }

    public static SingleResponse buildFailure(String errMessage) {
        return buildFailure(DEFAULT_FAIL_CODE,errMessage);
    }

    public static SingleResponse buildSuccess(){
        SingleResponse response = new SingleResponse();
        response.setSuccess(true);
        return response;
    }

}
