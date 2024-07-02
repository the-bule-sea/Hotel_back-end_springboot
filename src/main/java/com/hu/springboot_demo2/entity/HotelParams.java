package com.hu.springboot_demo2.entity;

public class HotelParams {
    private String city;
    private String aboutname;
    //    private String hotelDesp;
    private Integer pageNum;
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getAboutname() {
        return aboutname;
    }

    public void setAboutname(String aboutname) {
        this.aboutname = aboutname;
    }

    @Override
    public String toString() {
        return "HotelParams{" +
                "city='" + city + '\'' +
                ", aboutname='" + aboutname + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
