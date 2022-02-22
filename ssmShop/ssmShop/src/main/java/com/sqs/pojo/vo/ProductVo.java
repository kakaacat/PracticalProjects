package com.sqs.pojo.vo;

/**
 * @Author : kaka
 * @Date: 2022-02-22 16:32
 */
public class ProductVo {
    //封装所有页面上的查询条件
    private String pname;
    private Integer typrid;
    private Integer lprive;
    private Integer hprice;
    private Integer page = 1;

    public ProductVo() {
    }

    public ProductVo(String pname, Integer typrid, Integer lprive, Integer hprice, Integer page) {
        this.pname = pname;
        this.typrid = typrid;
        this.lprive = lprive;
        this.hprice = hprice;
        this.page = page;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getTyprid() {
        return typrid;
    }

    public void setTyprid(Integer typrid) {
        this.typrid = typrid;
    }

    public Integer getLprive() {
        return lprive;
    }

    public void setLprive(Integer lprive) {
        this.lprive = lprive;
    }

    public Integer getHprice() {
        return hprice;
    }

    public void setHprice(Integer hprice) {
        this.hprice = hprice;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "ProductVo{" +
                "pname='" + pname + '\'' +
                ", typrid=" + typrid +
                ", lprive=" + lprive +
                ", hprice=" + hprice +
                ", page=" + page +
                '}';
    }
}
