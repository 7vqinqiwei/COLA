package com.alibaba.cola.dto;

/**
 * commandExe接口  -- 一个命令器只执行一条命令
 * @author seven
 */
public interface CommandExecutorI<R extends Response,T extends Command> {

    /**
     * Executor执行命令通用Command
     * @param command
     * @return
     */
    R execute(T command);

}
