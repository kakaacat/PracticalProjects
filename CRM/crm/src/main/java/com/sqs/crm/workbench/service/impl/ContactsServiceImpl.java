package com.sqs.crm.workbench.service.impl;

import com.sqs.crm.workbench.mapper.ContactsMapper;
import com.sqs.crm.workbench.model.Contacts;
import com.sqs.crm.workbench.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Override
    public int saveContacts(Contacts contacts) {
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
}
