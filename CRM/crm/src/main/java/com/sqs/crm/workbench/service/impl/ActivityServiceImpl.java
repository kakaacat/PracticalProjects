package com.sqs.crm.workbench.service.impl;

import com.sqs.crm.workbench.mapper.ActivityMapper;
import com.sqs.crm.workbench.model.Activity;
import com.sqs.crm.workbench.model.FunnelVO;
import com.sqs.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<Activity> queryActivityByConditionForPage(Map<String, Object> map) {
        return activityMapper.selectActivityByConditionForPage(map);
    }

    @Override
    public int queryCountOfActivityByCondition(Map<String, Object> map) {
        return activityMapper.selectCountOfActivityByCondition(map);
    }

    @Override
    public int deleteActivityByIds(String[] ids) {
        return activityMapper.deleteActivityByIds(ids);
    }

    @Override
    public Activity queryActivityById(String id) {
        return activityMapper.selectActivityById(id);
    }

    @Override
    public int saveEditActivity(Activity activity) {
        return activityMapper.updateActivity(activity);
    }

    @Override
    public List<Activity> queryAllActivities() {
        return activityMapper.selectAllActivities();
    }

    @Override
    public List<Activity> queryActivitiesByIds(String[] ids) {
        return activityMapper.selectActivitiesByIds(ids);
    }

    @Override
    public int saveCreateActivitiesByList(List<Activity> activityList) {
        return activityMapper.insertActivitiesByList(activityList);
    }

    @Override
    public Activity queryActivityByIdForDetail(String id) {
        return activityMapper.selectActivityByIdForDetail(id);
    }

    @Override
    public List<Activity> queryActivityByClueIdForDetail(String clueId) {
        return activityMapper.selectActivityByClueIdForDetail(clueId);
    }

    @Override
    public List<Activity> queryActivityByNameClueIdForDetail(Map<String, Object> map) {
        return activityMapper.selectActivityByNameClueIDForDetail(map);
    }

    @Override
    public List<Activity> queryActivityByIdsForDetail(String[] ids) {
        return activityMapper.selectActivityByIdsForDetail(ids);
    }

    @Override
    public List<Activity> queryActivityByNameClueIdForConvert(Map<String, Object> map) {
        return activityMapper.selectActivityByNameClueIdForConvert(map);
    }

    @Override
    public List<Activity> queryActivityByNameForTrans(String name) {
        return activityMapper.selectActivityByNameForTrans(name);
    }

    @Override
    public List<FunnelVO> queryCountOfActivityGroupByUser() {
        return activityMapper.selectCountOfActivityGroupByUser();
    }
}
