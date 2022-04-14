package com.sqs.crm.workbench.web.controller;

import com.sqs.crm.settings.model.DicValue;
import com.sqs.crm.settings.model.User;
import com.sqs.crm.settings.service.DicValueService;
import com.sqs.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.PrivateKey;
import java.util.List;

/**
 * @Author : kaka
 * @Date: 2022-04-14 22:02
 */

@Controller
public class ClueController {
    @Autowired
    private UserService userService;
    @Autowired
    private DicValueService dicValueService;


    @RequestMapping("/workbench/clue/index.do")
    public String index(HttpServletRequest request) {
        //调用service层方法
        List<User> userList = userService.queryAllUsers();
        List<DicValue> appellationList = dicValueService.queryDicValueByTypeCode("appellation");
        List<DicValue> clueStateList = dicValueService.queryDicValueByTypeCode("clueState");
        List<DicValue> sourceList = dicValueService.queryDicValueByTypeCode("source");
        //把数据写入request中
        request.setAttribute("userList", userList);
        request.setAttribute("appellationList", appellationList);
        request.setAttribute("clueStateList", clueStateList);
        request.setAttribute("sourceList", sourceList);
        //请求转发
        return "workbench/clue/index";
    }

}
