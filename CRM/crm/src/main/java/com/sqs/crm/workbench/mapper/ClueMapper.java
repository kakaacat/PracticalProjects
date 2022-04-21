package com.sqs.crm.workbench.mapper;

import com.sqs.crm.workbench.model.Clue;

import java.util.List;
import java.util.Map;

public interface ClueMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Sat Apr 16 15:23:51 CST 2022
     */
    int deleteByPrimaryKey(String id);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Sat Apr 16 15:23:51 CST 2022
     */
    int insertSelective(Clue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Sat Apr 16 15:23:51 CST 2022
     */
    Clue selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Sat Apr 16 15:23:51 CST 2022
     */
    int updateByPrimaryKeySelective(Clue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue
     *
     * @mbggenerated Sat Apr 16 15:23:51 CST 2022
     */
    int updateByPrimaryKey(Clue record);

    /**
     * 保存创建的线索
     */
    int insert(Clue clue);

    /**
     * 根据条件分页查询列表
     */
    List<Clue> selectClueByConditionForPage(Map<String, Object> map);

    /**
     * 根据条件查询线索的总条数
     */
    int selectCountOfClueByCondition(Map<String, Object> map);

    /**
     * 根据Id查询线索明细信息
     */
    Clue selectClueByIdForDetail(String id);

}