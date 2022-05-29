package com.sqs.crm.workbench.service.impl;

import com.sqs.crm.workbench.mapper.ContactsMapper;
import com.sqs.crm.workbench.model.Contacts;
import com.sqs.crm.workbench.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : kaka
 * @Date: 2022-05-17 21:50
 */
@Service("contactsService")
public class ContactsServiceImpl implements ContactsService {
    @Autowired
    private ContactsMapper contactsMapper;


    @Override
    public int insertContacts(Contacts contacts) {
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
}
