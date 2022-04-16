package com.sqs.crm.workbench.service.impl;

import com.sqs.crm.workbench.mapper.ClueMapper;
import com.sqs.crm.workbench.model.Clue;
import com.sqs.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author : kaka
 * @Date: 2022-04-16 15:29
 */

@Service("clueService")
public class ClueServiceImpl implements ClueService {
    @Autowired
    private ClueMapper clueMapper;

    @Override
    public int saveClue(Clue clue) {
        return clueMapper.insert(clue);
    }
}
