package com.sqs.crm.workbench.service;

import com.sqs.crm.workbench.model.Contacts;

import java.util.List;

public interface ContactsService {
   List<Contacts> queryContactsByNameForCreateTrans(String name);
}