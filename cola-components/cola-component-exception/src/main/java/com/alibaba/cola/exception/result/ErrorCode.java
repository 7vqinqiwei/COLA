package com.alibaba.cola.exception.result;

/**
 * 错误类
 *
 * @author qi.wei
 */
public enum ErrorCode implements IErrorCode {
    /**
     * 全局常用异常定义
     */
    FAILED(0, "失败"),
    OK(200, "成功"),
    MOVED_PERMANENTLY(301, "请求的资源已经永久转移"),
    FOUND(302, "请重新发送请求"),
    BAD_REQUEST(400, "请求错误，请修正请求"),
    UNAUTHORIZED(401, "没有被授权或者授权已经失效"),
    FORBIDDEN(403, "请求被理解，但是拒绝执行"),
    NOT_FOUND(404, "访问的资源不存在"),
    METHOD_NOT_ALLOWED(405, "请求方法不允许被执行"),
    NOT_ACCEPTABLE(406, "请求的资源不满足请求者要求"),
    REQUEST_TIMEOUT(408, "请求超时"),
    GONE(410, "请求的资源不可用"),
    TOO_MANY_REQUESTS(429, "请求过多"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    BAD_GATEWAY(502, "响应无效"),
    SERVICE_UNAVAILABLE(503, "服务器维护或者过载，拒绝服务"),
    GATEWAY_TIMEOUT(504, "上游服务器超时"),
    HTTP_VERSION_NOT_SUPPORTED(505, "HTTP协议版本不支持"),
    DAO_EXCEPTION(600, "数据访问层异常"),
    SERVICE_EXCEPTION(601, "服务层异常"),
    INVALID_PARAMETERS(602, "无效的参数"),
    COMPONENT_EXCEPTION(603, "中间件/组件异常");

    private Integer code;

    private String msg;

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    ErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
