package com.sqs.utils;

import java.util.UUID;

/**
 * @Author : kaka
 * @Date: 2022-02-26 21:16
 */
public class FileNameUtil {
    //根据UUID生成文件名
    public static String getUUIDFileName() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }
    //从请求头中提取文件名和类型
    public static String getRealFileName(String context) {
        // Content-Disposition: form-data; name="myfile"; filename="a_left.jpg"
        int index = context.lastIndexOf("=");
        String filename = context.substring(index + 2, context.length() - 1);
        return filename;
    }
    //根据给定的文件名和后缀截取文件名
    public static String getFileType(String fileName){
        //9527s.jpg
        int index = fileName.lastIndexOf(".");
        return fileName.substring(index);
    }
}
