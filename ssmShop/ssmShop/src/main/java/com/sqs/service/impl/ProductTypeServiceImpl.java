package com.sqs.service.impl;

import com.sqs.mapper.ProductTypeMapper;
import com.sqs.pojo.ProductType;
import com.sqs.pojo.ProductTypeExample;
import com.sqs.service.ProductTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : kaka
 * @Date: 2022-02-24 13:29
 */
@Service("ProductTypeServiceImpl")
public class ProductTypeServiceImpl implements ProductTypeService {
    //业务层（spring）必须有数据访问层（Mybatis）的对象
    private ProductTypeMapper productTypeMapper;

    @Override
    public List<ProductType> getAllType() {
        return productTypeMapper.selectByExample(new ProductTypeExample());
    }
}
