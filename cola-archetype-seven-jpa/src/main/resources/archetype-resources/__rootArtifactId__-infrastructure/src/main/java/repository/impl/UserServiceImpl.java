package com.alibaba.demo.repository.impl;

import com.alibaba.demo.api.UserServiceI;
import com.alibaba.demo.dto.UserModel;
import com.alibaba.demo.entity.UserEntity;
import com.alibaba.demo.repository.UserRepository;
import com.wayne.jpa.base.service.impl.BaseServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserModel, UserEntity, String> implements UserServiceI {

    @Resource
    private UserRepository userRepository;

    @Override
    public JpaRepository getRepository() {
        return userRepository;
    }
}
