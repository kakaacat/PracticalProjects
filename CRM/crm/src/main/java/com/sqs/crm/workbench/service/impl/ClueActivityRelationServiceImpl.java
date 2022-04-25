package com.sqs.crm.workbench.service.impl;

import com.sqs.crm.workbench.mapper.ClueActivityRelationMapper;
import com.sqs.crm.workbench.model.ClueActivityRelation;
import com.sqs.crm.workbench.service.ClueActivityRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : kaka
 * @Date: 2022-04-24 19:43
 */
@Service("clueActivityRelationService")
public class ClueActivityRelationServiceImpl implements ClueActivityRelationService {
    @Autowired
    ClueActivityRelationMapper clueActivityRelationMapper;

    @Override
    public int saveClueActivityRelationByList(List<ClueActivityRelation> list) {
        return clueActivityRelationMapper.insertClueActivityRelationByList(list);
    }

    @Override
    public int deleteCAaRelationByClueActId(ClueActivityRelation clueActivityRelation) {
        return clueActivityRelationMapper.deleteCARelationByClueAndActId(clueActivityRelation);
    }
}
