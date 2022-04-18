package com.sqs.crm.workbench.service;

import com.sqs.crm.workbench.model.ClueRemark;

import java.util.List;

public interface ClueRemarkService {
    List<ClueRemark> queryClueRemarkByClueIdForDetail(String clueId);
}
