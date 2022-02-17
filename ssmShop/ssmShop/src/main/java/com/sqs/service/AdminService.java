package com.sqs.service;

import com.sqs.pojo.Admin;

public interface AdminService {
    //完成登录验证
    Admin login(String name, String pwd);
}
