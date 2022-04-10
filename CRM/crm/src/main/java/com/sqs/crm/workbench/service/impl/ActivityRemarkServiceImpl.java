package com.sqs.crm.workbench.service.impl;

import com.sqs.crm.workbench.mapper.ActivityRemarkMapper;
import com.sqs.crm.workbench.model.ActivityRemark;
import com.sqs.crm.workbench.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : kaka
 * @Date: 2022-04-10 16:43
 */
@Service("activityRemarkService")
public class ActivityRemarkServiceImpl implements ActivityRemarkService {

    @Autowired
    private ActivityRemarkMapper activityRemarkMapper;

    @Override
    public List<ActivityRemark> queryActivityRemarkByActivityIdForDetail(String activityId) {
        return activityRemarkMapper.selectActivityRemarkByActivityIdForDetail(activityId);
    }

    @Override
    public int saveCreateActivityRemark(ActivityRemark activityRemark) {
        return activityRemarkMapper.insertActivityRemark(activityRemark);
    }
}
