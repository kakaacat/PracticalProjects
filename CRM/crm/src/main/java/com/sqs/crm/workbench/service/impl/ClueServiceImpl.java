package com.sqs.crm.workbench.service.impl;

import com.sqs.crm.commons.contants.Contants;
import com.sqs.crm.commons.utils.DateUtils;
import com.sqs.crm.commons.utils.UUIDUtils;
import com.sqs.crm.settings.model.User;
import com.sqs.crm.workbench.mapper.ClueMapper;
import com.sqs.crm.workbench.mapper.CustomerMapper;
import com.sqs.crm.workbench.model.Clue;
import com.sqs.crm.workbench.model.Contacts;
import com.sqs.crm.workbench.model.Customer;
import com.sqs.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author : kaka
 * @Date: 2022-04-16 15:29
 */

@Service("clueService")
public class ClueServiceImpl implements ClueService {
    @Autowired
    private ClueMapper clueMapper;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public int saveClue(Clue clue) {
        return clueMapper.insert(clue);
    }

    @Override
    public List<Clue> queryClueByConditionForPage(Map<String, Object> map) {
        return clueMapper.selectClueByConditionForPage(map);
    }

    @Override
    public int queryCountOfClueByCondition(Map<String, Object> map) {
        return clueMapper.selectCountOfClueByCondition(map);
    }

    @Override
    public Clue queryClueByIdForDetail(String id) {
        return clueMapper.selectClueByIdForDetail(id);
    }

    @Override
    public void saveConvertClue(Map<String, Object> map) {
        String id = (String) map.get("clueId");
        User user = (User) map.get(Contants.SESSION_USER);
        //根据id查询线索信息
        Clue clue = clueMapper.selectClueById(id);
        //把线索中有关公司的信息转换到客户表中
        Customer customer = new Customer();
        customer.setAddress(clue.getAddress());
        customer.setContactSummary(clue.getContactSummary());
        customer.setCreateBy(user.getId());
        customer.setCreateTime(DateUtils.formateDateTime(new Date()));
        customer.setDescription(clue.getDescription());
        customer.setId(UUIDUtils.getUUID());
        customer.setName(clue.getCompany());
        customer.setNextContactTime(clue.getNextContactTime());
        customer.setOwner(user.getId());
        customer.setPhone(clue.getPhone());
        customer.setWebsite(clue.getWebsite());
        customerMapper.insertCustomer(customer);
        //把该线索中有关个人的信息转到联系人表中
        Contacts contacts = new Contacts();
        contacts.setAddress(clue.getAddress());
        contacts.setAppellation(clue.getAppellation());
        contacts.setContactSummary(clue.getContactSummary());
        contacts.setCreateBy(user.getId());
        contacts.setCreateTime(DateUtils.formateDateTime(new Date()));
        contacts.setCustomerId();


    }
}
