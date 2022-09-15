package com.sqs.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sqs.reggie.entity.User;
import com.sqs.reggie.mapper.UserMapper;
import com.sqs.reggie.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author : kaka
 * @Date: 2022-09-15 16:55
 * @Description:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
