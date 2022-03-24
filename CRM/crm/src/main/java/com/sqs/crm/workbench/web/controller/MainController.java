package com.sqs.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author : kaka
 * @Date: 2022-03-22 19:31
 */
@Controller
public class MainController {

    @RequestMapping("/workbench/main/index.do")
    public String index(){
        return "workbench/main/index";
    }
}
