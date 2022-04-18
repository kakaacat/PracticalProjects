package com.sqs.crm.workbench.web.controller;

import com.sqs.crm.commons.contants.Contants;
import com.sqs.crm.commons.domain.ReturnObject;
import com.sqs.crm.commons.utils.DateUtils;
import com.sqs.crm.commons.utils.UUIDUtils;
import com.sqs.crm.settings.model.DicValue;
import com.sqs.crm.settings.model.User;
import com.sqs.crm.settings.service.DicValueService;
import com.sqs.crm.settings.service.UserService;
import com.sqs.crm.workbench.model.Activity;
import com.sqs.crm.workbench.model.Clue;
import com.sqs.crm.workbench.model.ClueRemark;
import com.sqs.crm.workbench.service.ActivityService;
import com.sqs.crm.workbench.service.ClueRemarkService;
import com.sqs.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.PrivateKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : kaka
 * @Date: 2022-04-14 22:02
 */

@Controller
public class ClueController {
    @Autowired
    private UserService userService;
    @Autowired
    private DicValueService dicValueService;
    @Autowired
    private ClueService clueService;
    @Autowired
    private ClueRemarkService clueRemarkService;
    @Autowired
    private ActivityService activityService;


    @RequestMapping("/workbench/clue/index.do")
    public String index(HttpServletRequest request) {
        //调用service层方法
        List<User> userList = userService.queryAllUsers();
        List<DicValue> appellationList = dicValueService.queryDicValueByTypeCode("appellation");
        List<DicValue> clueStateList = dicValueService.queryDicValueByTypeCode("clueState");
        List<DicValue> sourceList = dicValueService.queryDicValueByTypeCode("source");
        //把数据写入request中
        request.setAttribute("userList", userList);
        request.setAttribute("appellationList", appellationList);
        request.setAttribute("clueStateList", clueStateList);
        request.setAttribute("sourceList", sourceList);
        //请求转发
        return "workbench/clue/index";
    }

    @RequestMapping("/workbench/clue/saveClue.do")
    public @ResponseBody Object saveClue(Clue clue, HttpSession session) {
        User user = (User) session.getAttribute(Contants.SESSION_USER);

        //封装参数
        clue.setId(UUIDUtils.getUUID());
        clue.setCreateBy(user.getId());
        clue.setCreateTime(DateUtils.formateDateTime(new Date()));

        ReturnObject returnObject = new ReturnObject();
        try {
            int ret = clueService.saveClue(clue);

            if (ret > 0) {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            } else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage(Contants.RETURN_OBJECT_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage(Contants.RETURN_OBJECT_MESSAGE);
        }

        return returnObject;
    }

    @RequestMapping("/workbench/clue/queryClueByConditionForPage.do")
    public @ResponseBody Object queryClueByConditionForPage(String fullname, String company, String phone, String source,
                                                            String owner, String mphone, String state, int pageNo, int pageSize) {
        //封装参数
        Map<String, Object> map = new HashMap<>();
        map.put("fullname", fullname);
        map.put("company", company);
        map.put("phone", phone);
        map.put("source", source);
        map.put("owner", owner);
        map.put("mphone", mphone);
        map.put("state", state);
        map.put("beginNo", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);


        //调用service层方法
        List<Clue> clueList = clueService.queryClueByConditionForPage(map);
        int totalRows = clueService.queryCountOfClueByCondition(map);

        //根据查询结果，生成响应信息
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("clueList", clueList);
        retMap.put("totalRows", totalRows);

        return retMap;
    }

    @RequestMapping("/workbench/clue/clueDetail.do")
    public String clueDetail(String clueId, HttpServletRequest request) {
        //调用service层方法，查询数据
        Clue clue = clueService.queryClueByIdForDetail(clueId);
        List<ClueRemark> clueRemarkList = clueRemarkService.queryClueRemarkByClueIdForDetail(clueId);
        List<Activity> activityList = activityService.queryActivityByClueIdForDetail(clueId);
        //把数据保存到作用域中request
        request.setAttribute("clue", clue);
        request.setAttribute("clueRemarkList", clueRemarkList);
        request.setAttribute("activityList", activityList);
        //请求转发
        return "workbench/clue/detail";
    }
}
