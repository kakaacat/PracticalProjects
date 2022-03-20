package com.sqs.crm.settings.service.impl;

import com.sqs.crm.settings.mapper.UserMapper;
import com.sqs.crm.settings.model.User;
import com.sqs.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author : kaka
 * @Date: 2022-03-20 19:55
 */
@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserByLoginActAndPwd(Map<String, Object> map) {
        return userMapper.selectUserByLoginActAndPwd(map);
    }
}
