package com.sqs.crm.workbench.service;

import com.sqs.crm.workbench.model.Tran;

import java.util.Map;

public interface TranService {
    void saveCreateTran(Map<String, Object> map);

    Tran queryTranForDetailById(String id);
}
