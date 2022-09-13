package com.sqs.reggie.common;

/**
 * @Author : kaka
 * @Date: 2022-09-13 16:07
 * @Description: 基于ThreadLocal封装的工具类，用于保存和获取当前用户id
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    public static Long getCurrentId() {
        return threadLocal.get();
    }
}
