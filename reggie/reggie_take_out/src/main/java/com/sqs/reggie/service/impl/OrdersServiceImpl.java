package com.sqs.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sqs.reggie.entity.Orders;
import com.sqs.reggie.mapper.OrdersMapper;
import com.sqs.reggie.service.OrdersService;
import org.springframework.stereotype.Service;

/**
 * @Author : kaka
 * @Date: 2022-09-16 16:47
 * @Description:
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {
}
