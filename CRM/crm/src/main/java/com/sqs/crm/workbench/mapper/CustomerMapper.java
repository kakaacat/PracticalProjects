package com.sqs.crm.workbench.mapper;

import com.sqs.crm.workbench.model.Customer;

import java.util.List;

public interface CustomerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbggenerated Thu May 05 20:33:18 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbggenerated Thu May 05 20:33:18 CST 2022
     */
    int insert(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbggenerated Thu May 05 20:33:18 CST 2022
     */
    int insertSelective(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbggenerated Thu May 05 20:33:18 CST 2022
     */
    Customer selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbggenerated Thu May 05 20:33:18 CST 2022
     */
    int updateByPrimaryKeySelective(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbggenerated Thu May 05 20:33:18 CST 2022
     */
    int updateByPrimaryKey(Customer record);

    /**
     * 保存创建的客户
     */
    int insertCustomer(Customer customer);

    /**
     * 查询所有的客户
     */
    List<String> selectAllCustomerName();

    /**
     * 根据名称模糊查询客户名
     */
    List<String> selectCustomerByName(String customerName);

    /**
     * 根据名称精确查询客户
     */
    Customer selectCustomerDetailByName(String name);
}