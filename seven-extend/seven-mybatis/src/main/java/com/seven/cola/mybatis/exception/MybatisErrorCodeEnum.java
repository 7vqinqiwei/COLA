package com.seven.cola.mybatis.exception;

import com.alibaba.cola.dto.ErrorCodeI;

/**
 * @author qqw
 * create_date  2020/7/10 19:15
 * description     公共组件的错误码
 */

public enum MybatisErrorCodeEnum implements ErrorCodeI {

    /**
     *
     */
    REFLECTIVE_OPERATION_ERROR("00030001","Mybatis反射方法执行出错","请联系开发人员解决问题");

    private final String errCode;

    private final String errDesc;

    private final String correctGuid;

    MybatisErrorCodeEnum(String errCode, String errDesc, String correctGuid) {
        this.errCode = errCode;
        this.errDesc = errDesc;
        this.correctGuid = correctGuid;
    }

    /**
     * 错误码
     * @return errCode
     */
    @Override
    public String getErrCode() {
        return errCode;
    }

    /**
     * 错误描述
     *
     * @return errDesc
     */
    @Override
    public String getErrDesc() {
        return errDesc;
    }

    /**
     * 获取恢复错误的正确指导
     *
     * @return correctGuid
     */
    @Override
    public String getCorrectGuid() {
        return correctGuid;
    }
}
