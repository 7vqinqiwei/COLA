package com.alibaba.craftsman.command;

import com.alibaba.cola.cmdexe.CommandExeI;
import com.alibaba.cola.dto.Response;
import com.alibaba.craftsman.convertor.UserProfileConvertor;
import com.alibaba.craftsman.domain.user.UserProfile;
import com.alibaba.craftsman.dto.UserProfileUpdateCmd;
import com.alibaba.craftsman.domain.gateway.UserProfileGateway;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserProfileUpdateCmdExe implements CommandExeI<UserProfileUpdateCmd> {

    @Resource
    private UserProfileGateway userProfileGateway;

    @Override
    public Response execute(UserProfileUpdateCmd cmd) {
        UserProfile userProfile = UserProfileConvertor.toEntity(cmd.getUserProfileCO());
        userProfileGateway.update(userProfile);
        return Response.buildSuccess();
    }
}