package com.sqs.crm.workbench.service.impl;

import com.sqs.crm.workbench.mapper.TranRemarkMapper;
import com.sqs.crm.workbench.model.TranRemark;
import com.sqs.crm.workbench.service.TranRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : kaka
 * @Date: 2022-06-08 20:58
 */
@Service("tranRemarkService")
public class TranRemarkServiceImpl implements TranRemarkService {
    @Autowired
    private TranRemarkMapper tranRemarkMapper;

    @Override
    public List<TranRemark> queryTranRemarkByTranId(String tranId) {
        return tranRemarkMapper.selectTranRemarkByTranId(tranId);
    }
}
