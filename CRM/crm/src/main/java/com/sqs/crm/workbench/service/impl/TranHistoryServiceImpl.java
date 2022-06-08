package com.sqs.crm.workbench.service.impl;

import com.sqs.crm.workbench.mapper.TranHistoryMapper;
import com.sqs.crm.workbench.model.TranHistory;
import com.sqs.crm.workbench.service.TranHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : kaka
 * @Date: 2022-06-08 21:12
 * @Description: 类
 */
@Service("tranHistoryService")
public class TranHistoryServiceImpl implements TranHistoryService {
    @Autowired
    private TranHistoryMapper tranHistoryMapper;

    @Override
    public List<TranHistory> queryTranHistoryByTranId(String tranId) {
        return tranHistoryMapper.selectTranHistoryByTranId(tranId);
    }
}
