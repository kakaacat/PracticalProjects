package com.sqs.reggie.common;

/**
 * @Author : kaka
 * @Date: 2022-09-13 17:48
 * @Description: 自定义业务异常
 */
public class CustomException extends RuntimeException{
    public CustomException(String msg) {
        super(msg);
    }
}
