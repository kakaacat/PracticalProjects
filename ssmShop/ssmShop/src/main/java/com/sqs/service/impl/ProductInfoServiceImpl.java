package com.sqs.service.impl;

import com.sqs.mapper.ProductInfoMapper;
import com.sqs.pojo.ProductInfo;
import com.sqs.pojo.ProductInfoExample;
import com.sqs.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : kaka
 * @Date: 2022-02-22 15:42
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    //业务逻辑层有数据访问层的对象
    @Autowired
    ProductInfoMapper productInfoMapper;

    @Override
    public List<ProductInfo> getAll() {

        return productInfoMapper.selectByExample(new ProductInfoExample());
    }
}
