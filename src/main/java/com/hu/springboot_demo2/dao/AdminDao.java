package com.hu.springboot_demo2.dao;

import com.hu.springboot_demo2.entity.Admin;
import com.hu.springboot_demo2.entity.Params;
import com.hu.springboot_demo2.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface AdminDao extends Mapper<Admin> {

    //第一种
    //基于注解的方式


    //第一种
    //基于注解的方式

    // woc, 一开始用的findAdminBySearch，然后就注入失败，改名就好了，mybatis的要求好多啊啊啊啊啊！！！！
    @Select("SELECT AdminName, Password FROM admin WHERE admin.AdminName LIKE CONCAT('%', #{name}, '%')")
    List<Admin> AdminfindAdminBySearch(Map<String, Object> params);

    @Select("select * from admin where AdminName = #{name} limit 1")
    Admin findByName(@Param("name") String name);

    @Select("select * from admin where AdminName = #{adminName} and admin.Password = #{password} limit 1")
    Admin findByNameAndPassword(@Param("adminName") String adminName, @Param("password") String password);
}
