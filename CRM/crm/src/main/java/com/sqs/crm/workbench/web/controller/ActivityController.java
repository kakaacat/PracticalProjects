package com.sqs.crm.workbench.web.controller;

import com.sqs.crm.commons.contants.Contants;
import com.sqs.crm.commons.domain.ReturnObject;
import com.sqs.crm.commons.utils.DateUtils;
import com.sqs.crm.commons.utils.HSSFUtils;
import com.sqs.crm.commons.utils.UUIDUtils;
import com.sqs.crm.settings.model.User;
import com.sqs.crm.settings.service.UserService;
import com.sqs.crm.workbench.model.Activity;
import com.sqs.crm.workbench.service.ActivityService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

/**
 * @Author : kaka
 * @Date: 2022-03-24 11:29
 */
@Controller
public class ActivityController {

    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;

    @RequestMapping("/workbench/activity/index.do")
    public String index(HttpServletRequest request){
        List<User> userList = userService.queryAllUsers();
        request.setAttribute("userList", userList);
        return "workbench/activity/index";
    }

    @RequestMapping("/workbench/activity/saveCreateActivity.do")
    public @ResponseBody Object saveCreateActivity(Activity activity, HttpSession session){
        User user = (User) session.getAttribute(Contants.SESSION_USER);
        //封装参数
        activity.setId(UUIDUtils.getUUID());
        activity.setCreateTime(DateUtils.formateDateTime(new Date()));
        activity.setCreateBy(user.getId());

        ReturnObject object = new ReturnObject();
        try {
            //调用service层方法，保存创建的活动
            int ret = activityService.saveCreateActivity(activity);

            if (ret > 0) {
                object.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            } else {
                object.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                object.setMessage("系统繁忙，请稍后重试...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            object.setMessage("系统繁忙，请稍后重试...");
        }
        return object;
    }

    @RequestMapping("/workbench/activity/queryActivityByConditionForPage.do")
    public @ResponseBody Object queryActivityByConditionForPage(String name, String owner, String startDate,
                                                                String endDate, int pageNo, int pageSize){
        //封装参数
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("owner", owner);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("beginNo", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);

        //调service层方法，查询数据
        List<Activity> activityList = activityService.queryActivityByConditionForPage(map);
        int totalRows = activityService.queryCountOfActivityByCondition(map);

        //根据查询结果，生成响应信息
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("activityList", activityList);
        retMap.put("totalRows", totalRows);

        return retMap;
    }

    @RequestMapping("/workbench/activity/deleteActivityByIds.do")
    public @ResponseBody Object deleteActivityByIds(String[] id) {
        ReturnObject returnObject = new ReturnObject();
        try {
            //调用service层方法
            int ret = activityService.deleteActivityByIds(id);
            if (ret > 0) {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            } else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙，请稍后重试...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙，请稍后重试...");
        }

        return returnObject;
    }

    @RequestMapping("/workbench/activity/selectActivityById.do")
    public @ResponseBody Object selectActivityById(String id) {
        //调用service层方法
        Activity activity = activityService.queryActivityById(id);
        //根据查询结果返回相应信息
        return activity;
    }

    @RequestMapping("/workbench/activity/saveEditActivity.do")
    public @ResponseBody Object saveEditActivity(Activity activity, HttpSession session) {
        User user = (User) session.getAttribute(Contants.SESSION_USER);
        //封装参数
        activity.setEditTime(DateUtils.formateDateTime(new Date()));
        activity.setEditBy(user.getId());

        //调用service层方法,保存修改的市场活动
        ReturnObject returnObject = new ReturnObject();
        try {
            int ret = activityService.saveEditActivity(activity);

            if (ret > 0) {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            } else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙，请稍后重试...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙，请稍后重试...");
        }

        return returnObject;
    }

    @RequestMapping("/workbench/activity/exportAllActivities.do")
    public void exportAllActivities(HttpServletResponse response) throws Exception {
        //调用service层方法，查询所有市场活动
        List<Activity> activityList = activityService.queryAllActivities();
        //创建excel文件，并把市场活动写入文件
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("市场活动列表");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = null;
        String[] listHead = {"ID", "所有者", "名称", "开始日期", "结束日期", "成本", "描述", "创建时间", "创建者", "修改时间", "修改者"};

        for (int i = 0; i < listHead.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(listHead[i]);
        }

        //遍历activityList, 创建HSSFRow对象，生成所有的数据行
        if (activityList != null && activityList.size() > 0) {
            for (int i = 0; i < activityList.size(); i++) {

                Activity activity = activityList.get(i);

                row = sheet.createRow(i + 1);
                cell = row.createCell(0);
                cell.setCellValue(activity.getId());
                cell = row.createCell(1);
                cell.setCellValue(activity.getOwner());
                cell = row.createCell(2);
                cell.setCellValue(activity.getName());
                cell = row.createCell(3);
                cell.setCellValue(activity.getStartDate());
                cell = row.createCell(4);
                cell.setCellValue(activity.getEndDate());
                cell = row.createCell(5);
                cell.setCellValue(activity.getCost());
                cell = row.createCell(6);
                cell.setCellValue(activity.getDescription());
                cell = row.createCell(7);
                cell.setCellValue(activity.getCreateTime());
                cell = row.createCell(8);
                cell.setCellValue(activity.getCreateBy());
                cell = row.createCell(9);
                cell.setCellValue(activity.getEditTime());
                cell = row.createCell(10);
                cell.setCellValue(activity.getEditBy());
            }
        }
//        String filePath = "D:\\IntelliJ IDEA\\PracticalProjects\\CRM\\crm\\src\\main\\java\\com\\sqs\\crm\\files\\";
//        //根据wb对象生成excel文件
//        OutputStream os = new FileOutputStream(filePath+"activityList.xls");
//        wb.write(os);
//        //关闭资源
//        os.close();
//        wb.close();

        //把生成的文件下载到客户端
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=activityList.xls");
        OutputStream out = response.getOutputStream();
//        InputStream is = new FileInputStream(filePath+"activityList.xls");
//        byte[] buff = new byte[256];
//        int len = 0;
//        while ((len = is.read(buff)) != -1) {
//            out.write(buff, 0, len);
//        }

        //is.close();

        wb.write(out);

        wb.close();
        out.flush();
    }

    @RequestMapping("/workbench/activity/queryActivitiesByIds.do")
    public void queryActivitiesByIds(String[] id, HttpServletResponse response) throws Exception{
        //调用service层方法
        List<Activity> activityList = activityService.queryActivitiesByIds(id);
        //创建excel文件，并把市场活动写入文件
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("市场活动列表");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = null;
        String[] listHead = {"ID", "所有者", "名称", "开始日期", "结束日期", "成本", "描述", "创建时间", "创建者", "修改时间", "修改者"};

        for (int i = 0; i < listHead.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(listHead[i]);
        }

        //遍历activityList, 创建HSSFRow对象，生成所有的数据行
        if (activityList != null && activityList.size() > 0) {
            for (int i = 0; i < activityList.size(); i++) {

                Activity activity = activityList.get(i);

                row = sheet.createRow(i + 1);
                cell = row.createCell(0);
                cell.setCellValue(activity.getId());
                cell = row.createCell(1);
                cell.setCellValue(activity.getOwner());
                cell = row.createCell(2);
                cell.setCellValue(activity.getName());
                cell = row.createCell(3);
                cell.setCellValue(activity.getStartDate());
                cell = row.createCell(4);
                cell.setCellValue(activity.getEndDate());
                cell = row.createCell(5);
                cell.setCellValue(activity.getCost());
                cell = row.createCell(6);
                cell.setCellValue(activity.getDescription());
                cell = row.createCell(7);
                cell.setCellValue(activity.getCreateTime());
                cell = row.createCell(8);
                cell.setCellValue(activity.getCreateBy());
                cell = row.createCell(9);
                cell.setCellValue(activity.getEditTime());
                cell = row.createCell(10);
                cell.setCellValue(activity.getEditBy());
            }
        }
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=activityList.xls");
        OutputStream out = response.getOutputStream();

        wb.write(out);

        wb.close();
        out.flush();
    }

    @RequestMapping("/workbench/activity/importActivities.do")
    public @ResponseBody Object importActivities(MultipartFile activityFile, HttpSession session) {
        User user = (User) session.getAttribute(Contants.SESSION_USER);
        ReturnObject returnObject = new ReturnObject();

        try {
            //把上传的Excel文件写入磁盘目录
            String filename = activityFile.getOriginalFilename();
            File file = new File("D:\\IntelliJ IDEA\\PracticalProjects\\CRM\\crm\\src\\main\\java\\com\\sqs\\crm" +
                    "\\files\\server", filename);
            activityFile.transferTo(file);

            //解析excel文件，获取文件数据，并封装成activityList
            FileInputStream is = new FileInputStream("D:\\IntelliJ IDEA\\PracticalProjects\\CRM\\crm\\src\\main\\java" +
                    "\\com\\sqs\\crm\\files\\server" + filename);
            HSSFWorkbook wb = new HSSFWorkbook(is);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row = null;
            HSSFCell cell = null;
            List<Activity> activityList = new ArrayList<>();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {      //getLastRowNum()最后一行的下标
                row = sheet.getRow(i);

                Activity activity = new Activity();
                activity.setId(UUIDUtils.getUUID());
                activity.setOwner(user.getId());
                activity.setCreateBy(user.getId());
                activity.setCreateTime(DateUtils.formateDateTime(new Date()));

                for (int j = 0; j < row.getLastCellNum(); j++) {     //getLastCellNum()最后一列的下标+1
                    cell  = row.getCell(j);

                    String cellValue = HSSFUtils.getCellValueForStr(cell);
                    if (j == 0) {
                        activity.setName(cellValue);
                    } else if (j == 1) {
                        activity.setStartDate(cellValue);
                    } else if (j == 2) {
                        activity.setEndDate(cellValue);
                    } else if (j == 3) {
                        activity.setCost(cellValue);
                    } else if (j == 4) {
                        activity.setDescription(cellValue);
                    }
                }
                //每一行一个activity
                activityList.add(activity);
            }

            //调用service层方法，保存arrayList数据
            int ret = activityService.saveCreateActivitiesByList(activityList);

            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setRetDate(ret);
        } catch (IOException e) {
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙，请稍后重试...");
        }

        return returnObject;
    }

}
