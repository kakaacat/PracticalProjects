package com.sqs.crm.commons.domain;

/**
 * @Author : kaka
 * @Date: 2022-03-20 20:27
 */
public class ReturnObject {
    //处理成功或失败的标记，1--成功，0--失败
    private String code;
    //提示信息
    private String message;
    //其他数据
    private Object retDate;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getRetDate() {
        return retDate;
    }

    public void setRetDate(Object retDate) {
        this.retDate = retDate;
    }
}
