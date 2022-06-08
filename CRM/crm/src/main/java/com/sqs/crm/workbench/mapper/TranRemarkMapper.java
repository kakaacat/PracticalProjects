package com.sqs.crm.workbench.mapper;

import com.sqs.crm.workbench.model.TranRemark;

import java.util.List;

public interface TranRemarkMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Sat May 14 20:58:07 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Sat May 14 20:58:07 CST 2022
     */
    int insert(TranRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Sat May 14 20:58:07 CST 2022
     */
    int insertSelective(TranRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Sat May 14 20:58:07 CST 2022
     */
    TranRemark selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Sat May 14 20:58:07 CST 2022
     */
    int updateByPrimaryKeySelective(TranRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Sat May 14 20:58:07 CST 2022
     */
    int updateByPrimaryKey(TranRemark record);

    /**
     * 批量保存交易备注
     */
    int insertTranRemarkByList(List<TranRemark> tranRemarkList);

    /**
     * 根据tranId查询该交易下的交易备注信息
     */
    List<TranRemark> selectTranRemarkByTranId(String tranId);
}