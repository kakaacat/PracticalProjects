package com.sqs.crm.commons.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;

/**
 * @Author : kaka
 * @Date: 2022-04-09 11:41
 *
 * 关于Excel文件的工具类
 */
public class HSSFUtils {
    public static String getCellValueForStr(HSSFCell cell) {
        if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            return cell.getNumericCellValue()+"";
        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
            return cell.getBooleanCellValue()+"";
        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
            return cell.getCellFormula();
        } else {
            return "";
        }
    }
}
