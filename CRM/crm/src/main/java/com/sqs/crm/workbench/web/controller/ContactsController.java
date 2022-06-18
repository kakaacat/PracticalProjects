package com.sqs.crm.workbench.web.controller;

import com.sqs.crm.commons.contants.Contants;
import com.sqs.crm.commons.domain.ReturnObject;
import com.sqs.crm.commons.utils.DateUtils;
import com.sqs.crm.commons.utils.UUIDUtils;
import com.sqs.crm.settings.model.DicValue;
import com.sqs.crm.settings.model.User;
import com.sqs.crm.settings.service.DicValueService;
import com.sqs.crm.settings.service.UserService;
import com.sqs.crm.workbench.model.Contacts;
import com.sqs.crm.workbench.service.ContactsService;
import com.sqs.crm.workbench.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : kaka
 * @Date: 2022-05-29 19:57
 */
@Controller
public class ContactsController {
    @Autowired
    private UserService userService;
    @Autowired
    private DicValueService dicValueService;
    @Autowired
    private ContactsService contactsService;
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/workbench/contacts/index.do")
    public String index(HttpServletRequest request) {
        List<User> userList = userService.queryAllUsers();
        List<DicValue> appellationList = dicValueService.queryDicValueByTypeCode("appellation");
        List<DicValue> sourceList = dicValueService.queryDicValueByTypeCode("source");

        request.setAttribute("userList", userList);
        request.setAttribute("appellationList", appellationList);
        request.setAttribute("sourceList", sourceList);

        return "workbench/contacts/index";
    }

    @RequestMapping("/workbench/contacts/saveContacts.do")
    public @ResponseBody Object saveContacts(Contacts contacts, HttpSession session) {
        User user = (User) session.getAttribute(Contants.SESSION_USER);
        //封装参数
        contacts.setId(UUIDUtils.getUUID());
        contacts.setCreateBy(user.getId());
        contacts.setCreateTime(DateUtils.formateDateTime(new Date()));

        ReturnObject returnObject = new ReturnObject();
        //调用service层方法
        try {
            int ret = contactsService.saveContacts(contacts);
            if (ret > 0) {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            } else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage(Contants.RETURN_OBJECT_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage(Contants.RETURN_OBJECT_MESSAGE);
        }

        return returnObject;
    }

    @RequestMapping("/workbench/contacts/queryCustomerByName.do")
    public @ResponseBody Object queryCustomerByName(String name) {
        //调用service层方法
        List<String> customerNameList = customerService.queryCustomerByName(name);
        //返回响应信息
        return customerNameList;
    }

    @RequestMapping("/workbench/contacts/queryContactsForPage.do")
    public @ResponseBody Object queryContactsForPage(String owner, String fullname, String customerId,
                                String source, int pageNo, int pageSize) {

        //封装参数
        Map<String, Object> map = new HashMap<>();
        map.put("owner", owner);
        map.put("fullname", fullname);
        map.put("customerId", customerId);
        map.put("source", source);
        map.put("beginNo", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);

        //调用service层方法
        List<Contacts> contactsList = contactsService.queryContactsByConditionForPage(map);
        int totalRows = contactsService.queryCountOfContactsByCondition(map);

        //生成响应信息
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("contactsList", contactsList);
        retMap.put("totalRows", totalRows);
        return retMap;
    }
}
