package com.sqs.reggie.controller;

import com.sqs.reggie.service.OrderDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : kaka
 * @Date: 2022-09-16 16:51
 * @Description: 类
 */

@Slf4j
@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

}
