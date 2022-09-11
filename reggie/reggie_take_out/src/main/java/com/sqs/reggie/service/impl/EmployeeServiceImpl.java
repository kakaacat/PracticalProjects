package com.sqs.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sqs.reggie.entity.Employee;
import com.sqs.reggie.mapper.EmployeeMapper;
import com.sqs.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @Author : kaka
 * @Date: 2022-09-11 15:15
 */

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
