package com.sqs.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sqs.reggie.dto.SetmealDto;
import com.sqs.reggie.entity.Setmeal;

public interface SetmealService extends IService<Setmeal> {

    //保存套餐以及菜品信息
    public void saveWithDish(SetmealDto setmealDto);
}
