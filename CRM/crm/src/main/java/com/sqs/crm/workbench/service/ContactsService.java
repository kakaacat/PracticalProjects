package com.sqs.crm.workbench.service;

import com.sqs.crm.workbench.model.Contacts;

import java.util.List;
import java.util.Map;

public interface ContactsService {
   int saveContacts(Contacts contacts);

   List<Contacts> queryContactsByNameForCreateTrans(String name);

   List<Contacts> queryAllContactsForIndex();

   List<Contacts> queryContactsByConditionForPage(Map<String, Object> map);

   int queryCountOfContactsByCondition(Map<String, Object> map);
}
