package com.sqs.crm.workbench.service.impl;

import com.sqs.crm.commons.contants.Contants;
import com.sqs.crm.commons.utils.DateUtils;
import com.sqs.crm.commons.utils.UUIDUtils;
import com.sqs.crm.settings.model.User;
import com.sqs.crm.workbench.mapper.CustomerMapper;
import com.sqs.crm.workbench.mapper.TranHistoryMapper;
import com.sqs.crm.workbench.mapper.TranMapper;
import com.sqs.crm.workbench.model.Customer;
import com.sqs.crm.workbench.model.Tran;
import com.sqs.crm.workbench.model.TranHistory;
import com.sqs.crm.workbench.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @Author : kaka
 * @Date: 2022-06-04 17:26
 */
@Service("tranService")
public class TranServiceImpl implements TranService {
    @Autowired
    private TranMapper tranMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private TranHistoryMapper tranHistoryMapper;

    @Override
    public void saveCreateTran(Map<String, Object> map) {
        String customerName = (String)map.get("customerName");
        User user = (User) map.get(Contants.SESSION_USER);
        //根据name查询客户
        Customer customer = customerMapper.selectCustomerDetailByName(customerName);
        //创建客户
        if (customer == null) {
            customer = new Customer();
            customer.setCreateBy(user.getId());
            customer.setCreateTime(DateUtils.formateDateTime(new Date()));
            customer.setId(UUIDUtils.getUUID());
            customer.setOwner(user.getId());
            customer.setName(customerName);
            //保存创建的客户
            customerMapper.insertCustomer(customer);
        }

        //保存交易
        Tran tran = new Tran();
        tran.setOwner((String) map.get("owner"));
        tran.setStage((String) map.get("stage"));
        tran.setNextContactTime((String) map.get("nextContactTime"));
        tran.setName((String) map.get("name"));
        tran.setMoney((String) map.get("money"));
        tran.setId(UUIDUtils.getUUID());
        tran.setExpectedDate((String) map.get("expectedDate"));
        tran.setCustomerId(customer.getId());
        tran.setCreateTime(DateUtils.formateDateTime(new Date()));
        tran.setCreateBy(user.getId());
        tran.setContactSummary((String) map.get("contactSummary"));
        tran.setContactsId((String) map.get("contactsId"));
        tran.setActivityId((String) map.get("activityId"));
        tran.setDescription((String) map.get("description"));
        tran.setSource((String) map.get("source"));
        tran.setType((String) map.get("type"));
        tranMapper.insertTran(tran);
        //保存交易历史
        TranHistory tranHistory = new TranHistory();
        tranHistory.setCreateBy(tran.getCreateBy());
        tranHistory.setCreateTime(tran.getCreateTime());
        tranHistory.setExpectedDate(tran.getExpectedDate());
        tranHistory.setId(UUIDUtils.getUUID());
        tranHistory.setMoney(tran.getMoney());
        tranHistory.setStage(tran.getStage());
        tranHistory.setTranId(tran.getId());
        tranHistoryMapper.insertTranHistory(tranHistory);
    }
}
