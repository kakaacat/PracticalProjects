package com.sqs.crm.workbench.web.controller;

import com.sqs.crm.settings.model.User;
import com.sqs.crm.settings.service.UserService;
import com.sqs.crm.workbench.mapper.ContactsMapper;
import com.sqs.crm.workbench.model.Contacts;
import com.sqs.crm.workbench.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author : kaka
 * @Date: 2022-05-29 15:43
 */
@Controller
public class CustomerController {

    @Autowired
    private UserService userService;
    @Autowired
    private ContactsService contactsService;

    @RequestMapping("/workbench/customer/index.do")
    public String index(HttpServletRequest request){
        List<User> userList = userService.queryAllUsers();
        request.setAttribute("userList", userList);
        return "workbench/customer/index";
    }
}
