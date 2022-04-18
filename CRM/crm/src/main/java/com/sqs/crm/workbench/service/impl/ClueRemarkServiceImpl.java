package com.sqs.crm.workbench.service.impl;

import com.sqs.crm.workbench.mapper.ClueRemarkMapper;
import com.sqs.crm.workbench.model.ClueRemark;
import com.sqs.crm.workbench.service.ClueRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : kaka
 * @Date: 2022-04-18 21:07
 */

@Service("clueRemarkService")
public class ClueRemarkServiceImpl implements ClueRemarkService {
    @Autowired
    private ClueRemarkMapper clueRemarkMapper;

    @Override
    public List<ClueRemark> queryClueRemarkByClueIdForDetail(String clueId) {
        return clueRemarkMapper.selectClueRemarkByClueIdForDetail(clueId);
    }
}
