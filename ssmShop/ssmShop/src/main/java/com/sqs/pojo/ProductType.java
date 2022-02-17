package com.sqs.pojo;

/**
 * @Author : kaka
 * @Date: 2022-02-17 15:30
 * @Description: 商品类型
 */
public class ProductType {
    private Integer typeId;

    private String typeName;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }
}
