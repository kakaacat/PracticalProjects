package com.sqs.crm.workbench.web.controller;

import com.sqs.crm.settings.model.User;
import com.sqs.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author : kaka
 * @Date: 2022-03-24 11:29
 */
@Controller
public class ActivityController {

    @Autowired
    private UserService userService;

    @RequestMapping("/workbench/activity/index.do")
    public String index(HttpServletRequest request){
        List<User> userList = userService.queryAllUsers();
        request.setAttribute("userList", userList);
        return "workbench/activity/index";
    }
}
