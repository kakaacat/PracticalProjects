package com.sqs.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sqs.reggie.entity.DishFlavor;
import com.sqs.reggie.mapper.DishFlavorMapper;
import com.sqs.reggie.service.DishFlavorService;
import org.springframework.stereotype.Service;

/**
 * @Author : kaka
 * @Date: 2022-09-14 14:10
 * @Description:
 */

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
