package com.sqs.crm.workbench.web.controller;

import com.sqs.crm.settings.mapper.DicValueMapper;
import com.sqs.crm.settings.mapper.UserMapper;
import com.sqs.crm.settings.model.DicValue;
import com.sqs.crm.settings.model.User;
import com.sqs.crm.settings.service.DicValueService;
import com.sqs.crm.settings.service.UserService;
import com.sqs.crm.workbench.model.Activity;
import com.sqs.crm.workbench.model.Contacts;
import com.sqs.crm.workbench.service.ActivityService;
import com.sqs.crm.workbench.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author : kaka
 * @Date: 2022-05-15 21:46
 */
@Controller
public class TransactionController {
    @Autowired
    private DicValueService dicValueService;
    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private ContactsService contactsService;

    @RequestMapping("/workbench/transaction/index.do")
    public String index(HttpServletRequest request){
        //调用service层方法，查询数据
        List<DicValue> transactionTypeList = dicValueService.queryDicValueByTypeCode("transactionType");
        List<DicValue> sourceList = dicValueService.queryDicValueByTypeCode("source");
        List<DicValue> stageList = dicValueService.queryDicValueByTypeCode("stage");
        //把数据保存到作用域
        request.setAttribute("transactionTypeList", transactionTypeList);
        request.setAttribute("sourceList", sourceList);
        request.setAttribute("stageList", stageList);
        //请求转发
        return "workbench/transaction/index";
    }

    @RequestMapping("/workbench/transaction/toSave.do")
    public String toSave(HttpServletRequest request) {
        //调用service层方法，查询数据
        List<User> userList = userService.queryAllUsers();
        List<DicValue> transactionTypeList = dicValueService.queryDicValueByTypeCode("transactionType");
        List<DicValue> sourceList = dicValueService.queryDicValueByTypeCode("source");
        List<DicValue> stageList = dicValueService.queryDicValueByTypeCode("stage");
        //把数据保存到作用域
        request.setAttribute("userList", userList);
        request.setAttribute("transactionTypeList", transactionTypeList);
        request.setAttribute("sourceList", sourceList);
        request.setAttribute("stageList", stageList);
        //请求转发
        return "workbench/transaction/save";
    }

    @RequestMapping("/workbench/transaction/queryActivityByNameForTrans.do")
    public @ResponseBody Object queryActivityByNameForTrans(String name) {
        //调用service层方法
        List<Activity> activityList = activityService.queryActivityByNameForTrans(name);
        //生成响应信息
        return activityList;
    }

    @RequestMapping("/workbench/transaction/queryContactsByNameForCreateTran.do")
    public @ResponseBody Object queryContactsByNameForCreateTran(String name) {
        //调用service层方法
        List<Contacts> contactsList = contactsService.queryContactsByNameForCreateTrans(name);
        //返回响应信息
        return contactsList;
    }


}
