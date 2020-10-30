package com.alibaba.craftsman.command;

import com.alibaba.cola.dto.CommandExeI;
import com.alibaba.cola.dto.Response;
import com.alibaba.craftsman.convertor.UserProfileConvertor;
import com.alibaba.craftsman.domain.gateway.UserProfileGateway;
import com.alibaba.craftsman.domain.user.UserProfile;
import com.alibaba.craftsman.dto.UserProfileAddCmd;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * UserProfileAddCmdExe
 *
 * @author Frank Zhang
 * @date 2019-02-28 6:25 PM
 */
@Component
public class UserProfileAddCmdExe implements CommandExeI<UserProfileAddCmd> {

    @Resource
    private UserProfileGateway userProfileGateway;

    @Override
    public Response execute(UserProfileAddCmd cmd) {
        UserProfile userProfile = UserProfileConvertor.toEntity(cmd.getUserProfileCO());
        userProfileGateway.create(userProfile);
        return Response.buildSuccess();
    }
}
