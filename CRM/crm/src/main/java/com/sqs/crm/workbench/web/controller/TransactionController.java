package com.sqs.crm.workbench.web.controller;

import com.sqs.crm.settings.mapper.DicValueMapper;
import com.sqs.crm.settings.model.DicValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author : kaka
 * @Date: 2022-05-15 21:46
 */
@Controller
public class TransactionController {
    @Autowired
    private DicValueMapper dicValueMapper;

    @RequestMapping("/workbench/transaction/index.do")
    public String index(HttpServletRequest request){
        //调用service层方法，查询数据
        List<DicValue> transactionTypeList = dicValueMapper.selectDicValueByTypeCode("transactionType");
        List<DicValue> sourceList = dicValueMapper.selectDicValueByTypeCode("source");
        //把数据保存到作用域
        request.setAttribute("transactionTypeList", transactionTypeList);
        request.setAttribute("sourceList", sourceList);
        //请求转发
        return "workbench/transaction/index";
    }
}
