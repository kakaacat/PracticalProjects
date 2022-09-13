package com.sqs.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sqs.reggie.entity.Setmeal;
import com.sqs.reggie.mapper.SetmealMapper;
import com.sqs.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author : kaka
 * @Date: 2022-09-13 17:33
 * @Description:
 */
@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
}
