package com.sqs.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sqs.mapper.ProductInfoMapper;
import com.sqs.pojo.ProductInfo;
import com.sqs.pojo.ProductInfoExample;
import com.sqs.pojo.vo.ProductVo;
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
    private ProductInfoMapper productInfoMapper;

    @Override
    public List<ProductInfo> getAll() {

        return productInfoMapper.selectByExample(new ProductInfoExample());
    }

    @Override
    public ProductInfo getById(Integer pid) {
        return productInfoMapper.selectByPrimaryKey(pid);
    }

    @Override
    public int save(ProductInfo info) {
        return productInfoMapper.insert(info);
    }

    @Override
    public int delete(Integer pid) {
        int num = 0;
        try {
            num = productInfoMapper.deleteByPrimaryKey(pid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public int update(ProductInfo info) {
        return productInfoMapper.updateByPrimaryKeySelective(info);
    }

    @Override
    public PageInfo splitPage(int page, int pageSize) {
        //商品分页一定会借助与PageHelper类，还要借助于ProductINfoExample
        ProductInfoExample example = new ProductInfoExample();
        //设置字符串是字段名称和排序规则
        example.setOrderByClause("p_id desc");
        //切记：在取集合之前，使用分页工具设置当前页和每页的记录数
        PageHelper.startPage(page, pageSize);
        //取集合
        List<ProductInfo> list = productInfoMapper.selectByExample(example);

        //将查到的集合封装进pageInfo
        PageInfo<ProductInfo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<ProductInfo> splitPageVo(ProductVo vo, int pageSize) {
        //切记切记：在取集合之前，使用分页插件一定要先设置当前页和每页的个数
        PageHelper.startPage(vo.getPage(),pageSize);
        //取集合
        List<ProductInfo> list=productInfoMapper.selectConditionSplitPage(vo);

        return new PageInfo<>(list);
    }

    @Override
    public int deleteBatch(String[] pids) {
        return 0;
    }
}
