package com.hu.springboot_demo2.dao;

import com.hu.springboot_demo2.entity.Params;
import com.hu.springboot_demo2.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface UserDao extends Mapper<User> {

    // 编写自定义的SQL语句
    // 根据用户名和密码查询用户信息
    @Select("SELECT * FROM user WHERE UserName = #{username} AND Password = #{password}")
    User findByNameAndPassword(@Param("username") String username, @Param("password") String password);
    //
    @Select("SELECT UserID,UserName,Points,UserType,Password,Phone,IDNumber FROM user WHERE user.UserName LIKE CONCAT('%', #{params.name}, '%') AND phone LIKE CONCAT('%', #{params.phone}, '%')")
    List<User> findBySearch(@Param("params") Params params);
    // 根据用户名查询用户信息
    @Select("SELECT UserName FROM user WHERE UserName = #{username}")
    User findByName(String uname);

    // 重复性检测
    @Select("SELECT user.UserName FROM user WHERE UserName = #{userName} UNION SElECT user.UserName FROM user WHERE Phone = #{phone} UNION SELECT UserName FROM user WHERE IDNumber = #{idNumber}")
    User findByNamePhoneIDNumber(@Param("userName") String userName, @Param("phone") String phone, @Param("idNumber") String idNumber);

    // 插入新用户
    @Insert("INSERT INTO user (UserName, Password, Phone, IDNumber) VALUES (#{userName}, #{password}, #{phone}, #{idNumber})")
    int insertUser(User user);

    // 后台查看用户界面--根据userId删除user
    @Delete("DELETE FROM user where UserID = #{userId}")
    void adminDeleteByID(Integer userId);

    // 后台查看用户界面--编辑user
    @Update("UPDATE user SET UserName=#{userName}, Points=#{points}, UserType=#{userType}, Password=#{password}, Phone=#{phone}, IDNumber=#{idNumber} WHERE userId=#{userId}")
    void updateUser(User user);

    // 通过bookingID返回userID
    @Select("SELECT UserID FROM bookinfo WHERE BookingID = #{bookingID}")
    Integer findUserByBookingID(Integer bookingID);

    // 通过userID查user
    @Select("SELECT user.UserID, user.Points, user.UserType FROM user WHERE user.UserID =#{userId}")
    User findUserByUserID(Integer userId);

    // vip功能中的保存用户
    @Update("UPDATE user SET Points = #{points}, UserType = #{userType} WHERE UserID = #{userId}")
    void saveUser(User user);

    // 判断vip
    @Select("SELECT user.UserType FROM user WHERE UserID = #{userId}")
    User findStatus(Integer userId);


}
