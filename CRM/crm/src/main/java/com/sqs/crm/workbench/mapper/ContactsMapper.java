package com.sqs.crm.workbench.mapper;

import com.sqs.crm.workbench.model.Contacts;
import com.sqs.crm.workbench.model.FunnelVO;

import java.util.List;
import java.util.Map;

public interface ContactsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts
     *
     * @mbggenerated Sun May 08 16:21:37 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts
     *
     * @mbggenerated Sun May 08 16:21:37 CST 2022
     */
    int insert(Contacts record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts
     *
     * @mbggenerated Sun May 08 16:21:37 CST 2022
     */
    int insertSelective(Contacts record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts
     *
     * @mbggenerated Sun May 08 16:21:37 CST 2022
     */
    Contacts selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts
     *
     * @mbggenerated Sun May 08 16:21:37 CST 2022
     */
    int updateByPrimaryKeySelective(Contacts record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts
     *
     * @mbggenerated Sun May 08 16:21:37 CST 2022
     */
    int updateByPrimaryKey(Contacts record);

    /**
     * 保存创建的联系人
     */
    int insertContacts(Contacts contacts);

    /**
     * 根据name模糊查询联系人
     */
    List<Contacts> selectContactsByNameForCreateTrans(String name);

    /**
     * 查询所有的联系人
     */
    List<Contacts> selectAllContactsForIndex();

    /**
     * 根据条件分页查询联系人
     */
    List<Contacts> selectContactsByConditionForPage(Map<String, Object> map);
    /**
     * 根据条件查询联系人的总条数
     */
    int selectCountOfContactsByCondition(Map<String, Object> map);

    /**
     * 根据客户分组查询联系人总数用于图表
     */
    List<FunnelVO> selectCountContactsGroupByCustomer();
}