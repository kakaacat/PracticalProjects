package com.sqs.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author : kaka
 * @Date: 2022-06-15 20:56
 */
@Controller
public class ChartController {

    @RequestMapping("/workbench/chart/transaction/index.do")
    public String index() {
        return "workbench/chart/transaction/index";
    }
}
