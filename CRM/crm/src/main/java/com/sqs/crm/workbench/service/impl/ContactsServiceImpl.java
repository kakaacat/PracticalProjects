package com.sqs.crm.workbench.service.impl;

import com.sqs.crm.commons.utils.DateUtils;
import com.sqs.crm.commons.utils.UUIDUtils;
import com.sqs.crm.workbench.mapper.ContactsMapper;
import com.sqs.crm.workbench.mapper.CustomerMapper;
import com.sqs.crm.workbench.model.Contacts;
import com.sqs.crm.workbench.model.Customer;
import com.sqs.crm.workbench.model.FunnelVO;
import com.sqs.crm.workbench.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author : kaka
 * @Date: 2022-05-17 21:50
 */
@Service("contactsService")
public class ContactsServiceImpl implements ContactsService {
    @Autowired
    private ContactsMapper contactsMapper;
    @Autowired
    private CustomerMapper customerMapper;


    @Override
    public int saveContacts(Contacts contacts) {
        String customerName = contacts.getCustomerId();
        //根据name查询客户
        Customer customer = customerMapper.selectCustomerDetailByName(customerName);
        //创建客户
        if (customer == null) {
            customer = new Customer();
            customer.setCreateBy(contacts.getCreateBy());
            customer.setCreateTime(DateUtils.formateDateTime(new Date()));
            customer.setId(UUIDUtils.getUUID());
            customer.setOwner(contacts.getCreateBy());
            customer.setName(customerName);
            //保存创建的客户
            customerMapper.insertCustomer(customer);
        }
        contacts.setCustomerId(customer.getId());
        return contactsMapper.insertContacts(contacts);
    }

    @Override
    public List<Contacts> queryContactsByNameForCreateTrans(String name) {
        return contactsMapper.selectContactsByNameForCreateTrans(name);
    }

    @Override
    public List<Contacts> queryAllContactsForIndex() {
        return contactsMapper.selectAllContactsForIndex();
    }

    @Override
    public List<Contacts> queryContactsByConditionForPage(Map<String, Object> map) {
        return contactsMapper.selectContactsByConditionForPage(map);
    }

    @Override
    public int queryCountOfContactsByCondition(Map<String, Object> map) {
        return contactsMapper.selectCountOfContactsByCondition(map);
    }

    @Override
    public List<FunnelVO> queryCountContactsGroupByCustomer() {
        return contactsMapper.selectCountContactsGroupByCustomer();
    }
}
