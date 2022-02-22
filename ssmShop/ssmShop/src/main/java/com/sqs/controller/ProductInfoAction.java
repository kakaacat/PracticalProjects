package com.sqs.controller;

import com.sqs.pojo.ProductInfo;
import com.sqs.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author : kaka
 * @Date: 2022-02-22 15:45
 */
@Controller
@RequestMapping("/prod")
public class ProductInfoAction {
    //界面层有业务逻辑层的对象
    @Autowired
    ProductInfoService productInfoService;

    @RequestMapping("/getAll")
    public String getAll(HttpServletRequest request) {
        List<ProductInfo> list = productInfoService.getAll();
        request.setAttribute("list", list);
        return "product";
    }
}
