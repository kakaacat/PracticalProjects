package com.sqs.crm.workbench.service;

import com.sqs.crm.workbench.model.ClueActivityRelation;

import java.util.List;

public interface ClueActivityRelationService {
    int saveClueActivityRelationByList(List<ClueActivityRelation> list);
}
