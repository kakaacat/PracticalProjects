package com.sqs.crm.workbench.web.controller;

import com.sqs.crm.workbench.model.FunnelVO;
import com.sqs.crm.workbench.service.ActivityService;
import com.sqs.crm.workbench.service.ClueService;
import com.sqs.crm.workbench.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : kaka
 * @Date: 2022-06-15 20:56
 */
@Controller
public class ChartController {
    @Autowired
    private TranService tranService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private ClueService clueService;

    @RequestMapping("/workbench/chart/transaction/index.do")
    public String index() {
        return "workbench/chart/transaction/index";
    }

    @RequestMapping("/workbench/chart/transaction/queryCountTranGroupByStage.do")
    public @ResponseBody Object queryCountTranGroupByStage() {
        List<FunnelVO> funnelVOList = tranService.queryCountTranGroupByStage();
        return funnelVOList;
    }


    @RequestMapping("/workbench/chart/activity/activityChartIndex.do")
    public String activityChartIndex() {
        return "workbench/chart/activity/index";
    }
    @RequestMapping("/workbench/chart/activity/queryCountActivityGroupByUser.do")
    public @ResponseBody Object queryCountActivityGroupByUser() {
        List<FunnelVO> funnelVOList = activityService.queryCountOfActivityGroupByUser();
        return funnelVOList;
    }

    @RequestMapping("/workbench/chart/clue/clueChartIndex.do")
    public String clueChartIndex() {
        return "workbench/chart/clue/index";
    }

    @RequestMapping("/workbench/chart/clue/queryCountClueGroupByStage.do")
    public @ResponseBody Object queryCountClueGroupByStage() {
        List<FunnelVO> funnelVOList = clueService.queryCountClueGroupByStage();
        List<String> xList = new ArrayList<>();
        for (FunnelVO funnelVO : funnelVOList) {
            xList.add(funnelVO.getName());
        }
        List<Integer> yList = new ArrayList<>();
        for (FunnelVO funnelVO : funnelVOList) {
            yList.add(funnelVO.getValue());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("xList", xList);
        map.put("yList", yList);

        return map;
    }
}
