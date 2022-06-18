package com.sqs.crm.workbench.service;

import com.sqs.crm.workbench.model.FunnelVO;
import com.sqs.crm.workbench.model.Tran;

import java.util.List;
import java.util.Map;

public interface TranService {
    void saveCreateTran(Map<String, Object> map);

    Tran queryTranForDetailById(String id);

    List<FunnelVO> queryCountTranGroupByStage();

    List<Tran> queryTranByConditionForPage(Map<String, Object> map);

    int queryCountOfTranByCondition(Map<String, Object> map);
}
