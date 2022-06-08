package com.sqs.crm.workbench.service;

import com.sqs.crm.workbench.model.TranRemark;

import java.util.List;

public interface TranRemarkService {
    List<TranRemark> queryTranRemarkByTranId(String tranId);
}
