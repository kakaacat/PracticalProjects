package com.sqs.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sqs.reggie.entity.Category;
import com.sqs.reggie.mapper.CategoryMapper;
import com.sqs.reggie.service.CategoryService;
import org.springframework.stereotype.Service;

/**
 * @Author : kaka
 * @Date: 2022-09-13 16:36
 */

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
