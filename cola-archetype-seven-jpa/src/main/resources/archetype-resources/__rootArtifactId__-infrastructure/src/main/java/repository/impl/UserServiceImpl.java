package ${package}.repository.impl;

import ${package}.api.UserServiceI;
import ${package}.dto.UserModel;
import ${package}.entity.UserEntity;
import ${package}.repository.UserRepository;
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
