package com.sqs.crm.workbench.service;

import com.sqs.crm.workbench.model.ActivityRemark;

import java.util.List;

public interface ActivityRemarkService {
    List<ActivityRemark> queryActivityRemarkByActivityIdForDetail(String activityId);

    int saveCreateActivityRemark(ActivityRemark activityRemark);
}
