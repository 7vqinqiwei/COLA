package com.alibaba.cola.cmdexe;


import com.alibaba.cola.dto.Command;
import com.alibaba.cola.dto.Response;

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
