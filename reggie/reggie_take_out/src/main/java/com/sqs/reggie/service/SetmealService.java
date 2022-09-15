package com.sqs.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sqs.reggie.dto.SetmealDto;
import com.sqs.reggie.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {

    //保存套餐以及菜品信息
    public void saveWithDish(SetmealDto setmealDto);

    //删除套餐以及关联菜品信息
    public void removeWithDish(List<Long> ids);
}
