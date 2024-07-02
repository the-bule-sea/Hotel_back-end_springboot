package com.hu.springboot_demo2.entity;

import javax.persistence.*;
import java.util.GregorianCalendar;

/*振中的教诲：命名不规范，差错两行泪。
请务必使用小驼峰命名
原来这里是UserName，导致前端数据一直和后端不一致。改了后就对的上了*/

@Table(name = "user")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "UserName")
    private String userName;

    @Column(name = "Points")
    private Integer points;

    @Column(name = "UserType")
    private String userType;

    @Column(name = "Password")
    private String password;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "IDNumber")
    private String idNumber;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //排错方法1，tostring重载


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", points=" + points +
                ", userType='" + userType + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", idNumber='" + idNumber + '\'' +
                '}';
    }
}
