package com.sqs.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sqs.reggie.entity.OrderDetail;
import com.sqs.reggie.mapper.OrderDetailMapper;
import com.sqs.reggie.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * @Author : kaka
 * @Date: 2022-09-16 16:48
 * @Description: 类
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
