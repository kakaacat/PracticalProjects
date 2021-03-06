package com.sqs.crm.workbench.mapper;

import com.sqs.crm.workbench.model.ClueActivityRelation;

import java.util.List;

public interface ClueActivityRelationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_activity_relation
     *
     * @mbggenerated Thu Apr 21 21:23:31 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_activity_relation
     *
     * @mbggenerated Thu Apr 21 21:23:31 CST 2022
     */
    int insert(ClueActivityRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_activity_relation
     *
     * @mbggenerated Thu Apr 21 21:23:31 CST 2022
     */
    int insertSelective(ClueActivityRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_activity_relation
     *
     * @mbggenerated Thu Apr 21 21:23:31 CST 2022
     */
    ClueActivityRelation selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_activity_relation
     *
     * @mbggenerated Thu Apr 21 21:23:31 CST 2022
     */
    int updateByPrimaryKeySelective(ClueActivityRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_activity_relation
     *
     * @mbggenerated Thu Apr 21 21:23:31 CST 2022
     */
    int updateByPrimaryKey(ClueActivityRelation record);

    /**
     * 插入线索市场活动关联关系
     */
    int insertClueActivityRelationByList(List<ClueActivityRelation> list);

    /**
     * 根据clueId和activityId删除关联关系
     */
    int deleteCARelationByClueAndActId(ClueActivityRelation clueActivityRelation);

    /**
     * 根据clueId查询该线索和市场活动的关联关系
     */
    List<ClueActivityRelation> selectClueActivityRelationByClueId(String clueId);

    /**
     * 根据clueId删除线索和市场活动关联关系
     */
    int deleteClueActivityRelationByClueId(String clueId);
}