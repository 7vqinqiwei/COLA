package com.alibaba.cola.cmdexe;

import com.alibaba.cola.dto.Command;
import com.alibaba.cola.dto.Response;

/**
 * commandExe接口  -- 一个命令器只执行一条命令
 * @author seven
 */
public interface CommandExeI<T extends Command> {

    /**
     * Executor执行命令通用Command
     * @param command
     * @return
     */
    Response execute(T command);

}
