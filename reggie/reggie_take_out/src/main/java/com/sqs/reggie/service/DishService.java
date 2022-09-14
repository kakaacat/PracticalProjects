package com.sqs.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sqs.reggie.dto.DishDto;
import com.sqs.reggie.entity.Dish;

public interface DishService extends IService<Dish> {

    //新增菜品， 同时插入菜品对应的口味，需要操作两个表：dish dishflavor
    public void saveWithFlavor(DishDto dishDto);

    //根据id查询菜品以及口味信息
    public DishDto getByIdWithFlavor(Long id);

    //修改菜品以及口味表
    public void updateWithFlavor(DishDto dishDto);
}
