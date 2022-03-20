package com.sqs.crm.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author : kaka
 * @Date: 2022-03-20 21:06
 * @describe： 对Date类型对象进行处理的工具类
 */
public class DateUtils {

    /**
     * 对指定的date对象进行格式化：yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String formateDateTime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(new Date());
        return dateStr;
    }
}
