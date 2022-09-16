package com.sqs.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sqs.reggie.common.BaseContext;
import com.sqs.reggie.common.R;
import com.sqs.reggie.entity.ShoppingCart;
import com.sqs.reggie.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : kaka
 * @Date: 2022-09-16 15:03
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;


    /**
     * 添加购物车
     * @param shoppingCart
     * @return
     */
    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart) {
        //获取当前用户id
        Long currentId = BaseContext.getCurrentId();
        shoppingCart.setUserId(currentId);

        //查询当前菜品或套餐是否在购物车中
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, shoppingCart.getUserId());
        if (shoppingCart.getDishId() != null) {
            //添加的是菜品
            queryWrapper.eq( ShoppingCart::getDishId, shoppingCart.getDishId());
        } else {
            //添加的是套餐
            queryWrapper.eq( ShoppingCart::getSetmealId, shoppingCart.getSetmealId());
        }

        ShoppingCart oneShoppingCart = shoppingCartService.getOne(queryWrapper);

        if (oneShoppingCart != null) {
            //已经存在就把数量字段加一
            oneShoppingCart.setNumber(oneShoppingCart.getNumber() + 1);
            shoppingCartService.updateById(oneShoppingCart);
        } else {
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartService.save(shoppingCart);
            oneShoppingCart = shoppingCart;
        }

        return R.success(oneShoppingCart);
    }


    /**
     * 展示购物车
     * @return
     */
    @GetMapping("/list")
    public R<List<ShoppingCart>> list() {
        //获取当前用户id
        Long currentId = BaseContext.getCurrentId();

        //查询购物车
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, currentId);
        queryWrapper.orderByAsc(ShoppingCart::getCreateTime);

        List<ShoppingCart> list = shoppingCartService.list(queryWrapper);

        return R.success(list);
    }

    /**
     * 清空购物车
     * @return
     */
    @DeleteMapping("/clean")
    public R<String> clean() {
        //获取当前用户id
        Long currentId = BaseContext.getCurrentId();

        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, currentId);
        shoppingCartService.remove(queryWrapper);

        return R.success("清空购物车成功");
    }

}
