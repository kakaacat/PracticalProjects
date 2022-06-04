package com.sqs.crm.workbench.service;

import com.sqs.crm.workbench.model.Customer;

import java.util.List;

public interface CustomerService {
    int saveCustomer(Customer customer);

    List<String> queryAllCustomerName();

    List<String> queryCustomerByName(String name);
}
