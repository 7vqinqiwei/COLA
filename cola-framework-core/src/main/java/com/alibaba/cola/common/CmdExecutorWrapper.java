package com.alibaba.cola.common;

import com.alibaba.cola.cmdexe.CmdExecutorWrapperI;
import com.alibaba.cola.dto.Command;
import com.alibaba.cola.cmdexe.CommandExecutorI;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.Assert;
import com.alibaba.cola.exception.ColaException;

/**
 * 执行器包装类
 * 一般的调用方都是注入**Exe然后执行其execute command方法
 * 该方法就是将该过程包装了一下 -- 后面需要对该方法的结果处理都可以增加注解的方式拓展
 * @author seven
 */
public class CmdExecutorWrapper implements CmdExecutorWrapperI {

    /**
     * 根据命令类型自动执行execute方法
     * @param command
     * @return
     */
    @Override
    public Response invoke(Command command){
        Assert.notNull(command);
        String commandName = command.getClass().getSimpleName();
        String executorName = commandName + ColaConstant.EXE_SUFFIX;
        Object cmdExeObj = ApplicationContextHelper.getBean(executorName);
        Assert.notNull(cmdExeObj);
        if (cmdExeObj instanceof CommandExecutorI) {
            CommandExecutorI cmdExe = (CommandExecutorI) cmdExeObj;
            return cmdExe.execute(command);
        } else {
            throw new ColaException("Cola System : Command Executor is not implements CommandExeI");
        }
    }

}
