package com.sqs.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sqs.reggie.entity.Category;
import com.sqs.reggie.entity.Dish;
import com.sqs.reggie.entity.Setmeal;
import com.sqs.reggie.mapper.CategoryMapper;
import com.sqs.reggie.service.CategoryService;
import com.sqs.reggie.service.DishService;
import com.sqs.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author : kaka
 * @Date: 2022-09-13 16:36
 */

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;

    /**
     * 根据id删除分类，删除之前进行关联判断
     * @param id
     */
    @Override
    public void remove(Long id) {
        //是否关联菜品
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int linkDishCount = dishService.count(dishLambdaQueryWrapper);
        if (linkDishCount > 0) {
            //有关联，抛出异常
        }

        //是否关联套餐
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        int linkSetmealCount = setmealService.count(setmealLambdaQueryWrapper);
        if (linkSetmealCount > 0) {
            //有关联，抛出异常
        }

        //正常删除
        super.removeById(id);

    }
}
