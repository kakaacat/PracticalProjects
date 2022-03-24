package com.sqs.crm.workbench.service.impl;

import com.sqs.crm.workbench.mapper.ActivityMapper;
import com.sqs.crm.workbench.model.Activity;
import com.sqs.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author : kaka
 * @Date: 2022-03-24 15:29
 */
@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public int saveCreateActivity(Activity activity) {
        return activityMapper.insertActivity(activity);
    }
}
