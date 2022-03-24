package com.sqs.crm.commons.utils;

import java.util.UUID;

/**
 * @Author : kaka
 * @Date: 2022-03-24 15:43
 */
public class UUIDUtils {

    /**
     * 获取UUID的值
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
