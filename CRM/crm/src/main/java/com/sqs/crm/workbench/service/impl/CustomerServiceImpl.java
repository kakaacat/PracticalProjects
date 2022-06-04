package com.sqs.crm.workbench.service.impl;

import com.sqs.crm.workbench.mapper.CustomerMapper;
import com.sqs.crm.workbench.model.Customer;
import com.sqs.crm.workbench.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : kaka
 * @Date: 2022-05-29 16:50
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public int saveCustomer(Customer customer) {
        return customerMapper.insertCustomer(customer);
    }

    @Override
    public List<String> queryAllCustomerName() {
        return customerMapper.selectAllCustomerName();
    }
}
