package com.sqs.service.impl;

import com.sqs.mapper.AdminMapper;
import com.sqs.pojo.Admin;
import com.sqs.pojo.AdminExample;
import com.sqs.service.AdminService;
import com.sqs.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.List;

/**
 * @Author : kaka
 * @Date: 2022-02-17 15:37
 */

@Service
public class AdminServiceImpl implements AdminService {

    //在业务逻辑层会有数据访问层的对象
    @Autowired
    AdminMapper adminMapper;

    @Override
    public Admin login(String name, String pwd) {
        //根据传入的用户名到数据库中查询相应的对象
        AdminExample example = new AdminExample();
        example.createCriteria().andANameEqualTo(name);

        List<Admin> list = adminMapper.selectByExample(example);
        if (list.size() > 0) {
            Admin admin = list.get(0);
            //如果得到了，就进行密码比对
            String miPwd = MD5Util.getMD5(pwd);
            if (miPwd.equals(admin.getaPass())) {
                return admin;
            }
//            if (pwd.equals(admin.getaPass())) {
//                return admin;
//            }
        }
        return null;
    }
}
