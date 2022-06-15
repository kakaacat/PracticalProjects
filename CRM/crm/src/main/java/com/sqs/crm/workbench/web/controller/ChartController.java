package com.sqs.crm.workbench.web.controller;

import com.sqs.crm.workbench.model.FunnelVO;
import com.sqs.crm.workbench.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author : kaka
 * @Date: 2022-06-15 20:56
 */
@Controller
public class ChartController {
    @Autowired
    private TranService tranService;

    @RequestMapping("/workbench/chart/transaction/index.do")
    public String index() {
        return "workbench/chart/transaction/index";
    }

    @RequestMapping("/workbench/chart/transaction/queryCountTranGroupByStage.do")
    public @ResponseBody Object queryCountTranGroupByStage() {
        List<FunnelVO> funnelVOList = tranService.queryCountTranGroupByStage();
        return funnelVOList;
    }
}
