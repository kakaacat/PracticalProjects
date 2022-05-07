package com.sqs.crm.workbench.service;

import com.sqs.crm.workbench.model.Clue;

import java.util.List;
import java.util.Map;

public interface ClueService {
    int saveClue(Clue clue);

    List<Clue> queryClueByConditionForPage(Map<String, Object> map);

    int queryCountOfClueByCondition(Map<String, Object> map);

    Clue queryClueByIdForDetail(String id);

    void saveConvertClue(Map<String, Object> map);
}
