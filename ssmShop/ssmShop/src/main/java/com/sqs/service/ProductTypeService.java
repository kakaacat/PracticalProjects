package com.sqs.service;

import com.sqs.pojo.ProductType;

import java.util.List;

public interface ProductTypeService {
    //完成查询全部商品类型的功能
    public List<ProductType> getAllType();
}
