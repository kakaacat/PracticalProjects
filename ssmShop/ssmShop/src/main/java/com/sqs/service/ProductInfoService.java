package com.sqs.service;

import com.github.pagehelper.PageInfo;
import com.sqs.pojo.ProductInfo;
import com.sqs.pojo.vo.ProductVo;

import java.util.List;

public interface ProductInfoService {
    //显示全部商品（不分页）
    List<ProductInfo> getAll();

    //根据主键查商品
    ProductInfo getById(Integer pid);

    //增加
    int save(ProductInfo info);

    //删除
    int delete(Integer pid);

    //修改
    int update(ProductInfo info);

    //分页
    PageInfo splitPage(int page, int pageSize);

    //多条件分页
    PageInfo<ProductInfo> splitPageVo(ProductVo vo, int pageSize);

    //批量删除
    int deleteBatch(String[] pids);

}
