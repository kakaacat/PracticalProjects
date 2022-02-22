package com.sqs.service;

import com.sqs.pojo.ProductInfo;

import java.util.List;

public interface ProductInfoService {
    //显示全部商品（不分页）
    List<ProductInfo> getAll();
}
