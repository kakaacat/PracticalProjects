package com.sqs.crm.workbench.web.controller;

import com.sqs.crm.settings.model.DicValue;
import com.sqs.crm.settings.model.User;
import com.sqs.crm.settings.service.DicValueService;
import com.sqs.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
}
