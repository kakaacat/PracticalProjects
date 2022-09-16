package com.sqs.reggie.controller;

import com.sqs.reggie.common.R;
import com.sqs.reggie.entity.Orders;
import com.sqs.reggie.service.OrderDetailService;
import com.sqs.reggie.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : kaka
 * @Date: 2022-09-16 16:49
 * @Description:
 */

@Slf4j
@RestController
@RequestMapping("/order")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;


    /**
     * 用户提交订单
     * @param orders
     * @return
     */
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders) {
        ordersService.submit(orders);

        return R.success("下单成功");
    }
}
