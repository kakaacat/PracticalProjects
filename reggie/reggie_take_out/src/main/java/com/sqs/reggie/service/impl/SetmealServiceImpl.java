package com.sqs.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sqs.reggie.dto.SetmealDto;
import com.sqs.reggie.entity.Setmeal;
import com.sqs.reggie.entity.SetmealDish;
import com.sqs.reggie.mapper.SetmealMapper;
import com.sqs.reggie.service.SetmealDishService;
import com.sqs.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author : kaka
 * @Date: 2022-09-13 17:33
 * @Description:
 */
@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
    @Autowired
    private SetmealDishService setmealDishService;


    /**
     * 保存套餐以及菜品信息
     * @param setmealDto
     */
    @Override
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {

        //保存套餐信息
        this.save(setmealDto);

        //保存菜品信息
        List<SetmealDish> list = setmealDto.getSetmealDishes();
        list = list.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        //保存
        setmealDishService.saveBatch(list);
    }
}
