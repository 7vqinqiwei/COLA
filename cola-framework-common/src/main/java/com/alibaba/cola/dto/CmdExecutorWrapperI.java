package com.alibaba.cola.dto;


/**
 * @author seven
 */
public interface CmdExecutorWrapperI {

    /**
     * 服务调用
     * @param command
     * @return
     */
    Response invoke(Command command);

}
