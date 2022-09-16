package com.sqs.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sqs.reggie.entity.ShoppingCart;
import com.sqs.reggie.mapper.ShoppingCartMapper;
import com.sqs.reggie.service.ShoppingCartService;
import org.springframework.stereotype.Service;

/**
 * @Author : kaka
 * @Date: 2022-09-16 15:03
 * @Description:
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
