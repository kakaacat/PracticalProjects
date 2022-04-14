package com.sqs.crm.settings.service;

import com.sqs.crm.settings.model.DicValue;

import java.util.List;

public interface DicValueService {
    List<DicValue> queryDicValueByTypeCode(String typeCode);
}
