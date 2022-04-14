package com.sqs.crm.settings.service.impl;

import com.sqs.crm.settings.mapper.DicValueMapper;
import com.sqs.crm.settings.model.DicValue;
import com.sqs.crm.settings.service.DicValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : kaka
 * @Date: 2022-04-14 21:59
 * */

@Service("dicValueService")
public class DicValueServiceImpl implements DicValueService {
    @Autowired
    private DicValueMapper dicValueMapper;

    @Override
    public List<DicValue> queryDicValueByTypeCode(String typeCode) {
        return dicValueMapper.selectDicValueByTypeCode(typeCode);
    }
}
