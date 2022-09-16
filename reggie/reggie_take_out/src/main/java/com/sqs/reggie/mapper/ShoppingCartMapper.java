package com.sqs.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sqs.reggie.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {
}
