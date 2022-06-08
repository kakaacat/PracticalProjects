package com.sqs.crm.workbench.service;

import com.sqs.crm.workbench.model.TranHistory;

import java.util.List;

public interface TranHistoryService {
    List<TranHistory> queryTranHistoryByTranId(String tranId);
}
