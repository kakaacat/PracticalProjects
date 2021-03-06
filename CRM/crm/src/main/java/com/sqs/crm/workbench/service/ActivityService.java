package com.sqs.crm.workbench.service;

import com.sqs.crm.workbench.model.Activity;
import com.sqs.crm.workbench.model.FunnelVO;

import java.util.List;
import java.util.Map;

public interface ActivityService {
    int saveCreateActivity(Activity activity);

    List<Activity> queryActivityByConditionForPage(Map<String, Object> map);

    int queryCountOfActivityByCondition(Map<String, Object> map);

    int deleteActivityByIds(String[] ids);

    Activity queryActivityById(String id);

    int saveEditActivity(Activity activity);

    List<Activity> queryAllActivities();

    List<Activity> queryActivitiesByIds(String[] ids);

    int saveCreateActivitiesByList(List<Activity> activityList);

    Activity queryActivityByIdForDetail(String id);

    List<Activity> queryActivityByClueIdForDetail(String clueId);

    List<Activity> queryActivityByNameClueIdForDetail(Map<String, Object> map);

    List<Activity> queryActivityByIdsForDetail(String[] ids);

    List<Activity> queryActivityByNameClueIdForConvert(Map<String, Object> map);

    List<Activity> queryActivityByNameForTrans(String name);

    List<FunnelVO> queryCountOfActivityGroupByUser();
}
