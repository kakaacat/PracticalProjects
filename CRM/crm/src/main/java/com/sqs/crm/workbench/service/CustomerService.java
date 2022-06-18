package com.sqs.crm.workbench.service;

import com.sqs.crm.workbench.model.Contacts;
import com.sqs.crm.workbench.model.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    int saveCustomer(Customer customer);

    List<String> queryAllCustomerName();

    List<String> queryCustomerByName(String name);

    List<Customer> queryCustomerByConditionForPage(Map<String, Object> map);

    int queryCountOfCustomerByCondition(Map<String, Object> map);
}
