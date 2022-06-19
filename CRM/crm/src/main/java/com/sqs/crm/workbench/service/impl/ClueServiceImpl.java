package com.sqs.crm.workbench.service.impl;

import com.sqs.crm.commons.contants.Contants;
import com.sqs.crm.commons.utils.DateUtils;
import com.sqs.crm.commons.utils.UUIDUtils;
import com.sqs.crm.settings.model.User;
import com.sqs.crm.workbench.mapper.*;
import com.sqs.crm.workbench.model.*;
import com.sqs.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
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
    @Autowired
    private ContactsMapper contactsMapper;
    @Autowired
    private ClueRemarkMapper clueRemarkMapper;
    @Autowired
    private CustomerRemarkMapper customerRemarkMapper;
    @Autowired
    private ContactsRemarkMapper contactsRemarkMapper;
    @Autowired
    private ClueActivityRelationMapper clueActivityRelationMapper;
    @Autowired
    private ContactsActivityRelationMapper contactsActivityRelationMapper;
    @Autowired
    private TranMapper tranMapper;
    @Autowired
    private TranRemarkMapper tranRemarkMapper;

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
        String clueId = (String) map.get("clueId");
        String isCreateTran = (String) map.get("isCreateTran");
        User user = (User) map.get(Contants.SESSION_USER);
        //1.根据id查询线索信息
        Clue clue = clueMapper.selectClueById(clueId);
        //2.把线索中有关公司的信息转换到客户表中
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
        //3.把该线索中有关个人的信息转到联系人表中
        Contacts contacts = new Contacts();
        contacts.setAddress(clue.getAddress());
        contacts.setAppellation(clue.getAppellation());
        contacts.setContactSummary(clue.getContactSummary());
        contacts.setCreateBy(user.getId());
        contacts.setCreateTime(DateUtils.formateDateTime(new Date()));
        contacts.setCustomerId(customer.getId());
        contacts.setDescription(clue.getDescription());
        contacts.setEmail(clue.getEmail());
        contacts.setFullname(clue.getFullname());
        contacts.setId(UUIDUtils.getUUID());
        contacts.setJob(clue.getJob());
        contacts.setMphone(clue.getMphone());
        contacts.setNextContactTime(clue.getNextContactTime());
        contacts.setOwner(user.getId());
        contacts.setSource(clue.getSource());
        contactsMapper.insertContacts(contacts);
        //4.查询该线索下的所有备注
        List<ClueRemark> clueRemarkList = clueRemarkMapper.selectClueRemarkByClueId(clue.getId());
        if (clueRemarkList != null && clueRemarkList.size() > 0) {
            //4-1-2.把备注转换到客户备注和联系人备注中
            List<CustomerRemark> customerRemarkList = new ArrayList<>();
            List<ContactsRemark> contactsRemarkList = new ArrayList<>();
            for (ClueRemark clueRemark : clueRemarkList) {
                CustomerRemark customerRemark = new CustomerRemark();
                customerRemark.setCreateBy(clueRemark.getCreateBy());
                customerRemark.setCreateTime(clueRemark.getCreateTime());
                customerRemark.setCustomerId(customer.getId());
                customerRemark.setEditBy(clueRemark.getEditBy());
                customerRemark.setEditFlag(clueRemark.getEditFlag());
                customerRemark.setEditTime(clueRemark.getEditTime());
                customerRemark.setId(UUIDUtils.getUUID());
                customerRemark.setNoteContent(clueRemark.getNoteContent());
                customerRemarkList.add(customerRemark);

                ContactsRemark contactsRemark = new ContactsRemark();
                contactsRemark.setContactsId(contacts.getId());
                contactsRemark.setCreateBy(clueRemark.getCreateBy());
                contactsRemark.setCreateTime(clueRemark.getCreateTime());
                contactsRemark.setEditBy(clueRemark.getEditBy());
                contactsRemark.setEditFlag(clueRemark.getEditFlag());
                contactsRemark.setEditTime(clueRemark.getEditTime());
                contactsRemark.setId(UUIDUtils.getUUID());
                contactsRemark.setNoteContent(clueRemark.getNoteContent());
                contactsRemarkList.add(contactsRemark);

            }
            customerRemarkMapper.insertCustomerRemarkByList(customerRemarkList);
            contactsRemarkMapper.insertContactsRemarkByList(contactsRemarkList);
        }

        //5.根据clueId查询该线索和市场活动关联关系
        List<ClueActivityRelation> clueActivityRelationList = clueActivityRelationMapper.selectClueActivityRelationByClueId(clueId);
        //6.线索和市场活动关联关系转换到联系人和市场活动关联关系中
        if (clueActivityRelationList != null && clueActivityRelationList.size() > 0) {
            List<ContactsActivityRelation> contactsActivityRelationList = new ArrayList<>();
            for (ClueActivityRelation clueActivityRelation : clueActivityRelationList) {
                ContactsActivityRelation contactsActivityRelation = new ContactsActivityRelation();
                contactsActivityRelation.setActivityId(clueActivityRelation.getActivityId());
                contactsActivityRelation.setContactsId(contacts.getId());
                contactsActivityRelation.setId(UUIDUtils.getUUID());
                contactsActivityRelationList.add(contactsActivityRelation);
            }
            contactsActivityRelationMapper.insertContactsActivityRelationByList(contactsActivityRelationList);
        }

        //7.如果需要创建交易 就往数据库添加交易
        if (isCreateTran.equals("true")){
            Tran tran = new Tran();
            tran.setActivityId((String) map.get("activityId"));
            tran.setContactsId(contacts.getId());
            tran.setCreateBy(user.getId());
            tran.setCreateTime(DateUtils.formateDateTime(new Date()));
            tran.setCustomerId(customer.getId());
            tran.setExpectedDate((String) map.get("expectedDate"));
            tran.setId(UUIDUtils.getUUID());
            tran.setStage((String) map.get("stage"));
            tran.setMoney((String) map.get("money"));
            tran.setName((String) map.get("name"));
            tran.setOwner(user.getId());
            tranMapper.insertTran(tran);

            //8.如果需要创建交易 把该线索备注表转换到交易备注表中
            if (clueRemarkList != null && clueRemarkList.size() > 0) {
                List<TranRemark> tranRemarkList = new ArrayList<>();
                for (ClueRemark clueRemark : clueRemarkList) {
                    TranRemark tranRemark = new TranRemark();
                    tranRemark.setId(UUIDUtils.getUUID());
                    tranRemark.setCreateBy(clueRemark.getCreateBy());
                    tranRemark.setCreateTime(clueRemark.getCreateTime());
                    tranRemark.setEditBy(clueRemark.getEditBy());
                    tranRemark.setEditFlag(clueRemark.getEditFlag());
                    tranRemark.setEditTime(clueRemark.getEditTime());
                    tranRemark.setNoteContent(clueRemark.getNoteContent());
                    tranRemark.setTranId(tran.getId());
                    tranRemarkList.add(tranRemark);
                }
                tranRemarkMapper.insertTranRemarkByList(tranRemarkList);
            }
        }
        //9.删除该线索下的所有备注
        clueRemarkMapper.deleteClueRemarkByClueId(clueId);
        //10.删除线索和市场活动关联关系
        clueActivityRelationMapper.deleteClueActivityRelationByClueId(clueId);
        //11.删除该线索
        clueMapper.deleteClueById(clueId);
    }

    @Override
    public List<FunnelVO> queryCountClueGroupByStage() {
        return clueMapper.selectCountClueGroupByStage();
    }
}
