package com.hu.springboot_demo2.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hu.springboot_demo2.dao.HotelDao;
import com.hu.springboot_demo2.dao.UserDao;
import com.hu.springboot_demo2.entity.Hotel;
import com.hu.springboot_demo2.entity.HotelParams;
import com.hu.springboot_demo2.entity.Params;
import com.hu.springboot_demo2.entity.User;
import com.hu.springboot_demo2.exception.CustomException;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private HotelDao hotelDao;
    @Autowired
    private UserDao userDao;


    public PageInfo<Hotel> findBySearch(HotelParams hotelParams){
        PageHelper.startPage(hotelParams.getPageNum(), hotelParams.getPageSize());
        // 接下来会自动按照当前开启的分页设置来查询
        // 得新建一个list
        List<Hotel> list;
        // 判断如果hotelParams返回的是city，就调用city。否则调用aboutname
        if(hotelParams.getCity() != null && !hotelParams.getCity().isEmpty()){
            list = hotelDao.findByCity(hotelParams);
        }else{
            list = hotelDao.findByName(hotelParams);
        }
        System.out.println("UserService查询到的数据："+list.toString());
        return PageInfo.of(list);
    }

    public User login(User user) {
        // 1. 进行非空判断
        if(user.getUserName() == null || "".equals(user.getUserName())){
            throw new CustomException("用户名不能为空");
        }

        // 1.5 进行密码为空判断
        if(user.getPassword() == null || "".equals(user.getPassword())){
            throw new CustomException("密码不能为空");
        }

        // 2. 查询数据库
        //无语了啊啊啊啊,这里一开始写反了，导致用户名和密码反了，报错半天
        User userfind = userDao.findByNameAndPassword(user.getUserName(), user.getPassword());
        if(userfind == null){
            //说明密码或者账户错误
            throw new CustomException("用户名或密码错误");
        }
        return userfind;

    }

    public User register(User user) {

        // 1. 进行非空判断
        if(user.getUserName() == null || "".equals(user.getUserName())){
            throw new CustomException("用户名不能为空");
        }

        // 1.2 进行密码为空判断
        if(user.getPassword() == null || "".equals(user.getPassword())){
            throw new CustomException("密码不能为空");
        }

        // 1.2 进行手机号为空判断
        if(user.getPhone() == null || "".equals(user.getPhone())){
            throw new CustomException("手机号不能为空");
        }

        // 1.3 进行身份证号为空判断
        if(user.getIdNumber() == null || "".equals(user.getIdNumber())){
            throw new CustomException("身份证号不能为空");
        }

        // 2. 进行重复性判断
        User userfind = userDao.findByNamePhoneIDNumber(user.getUserName(),user.getPhone(),user.getIdNumber());
        if(userfind != null){
            //说明用户名已存在
            throw new CustomException("用户已存在,请勿重复添加");
        }

        // 3. 进行插入操作
        // System.out.println(user.toString());
        userDao.insertUser(user);

        return user;
    }

    public PageInfo<User> findBySearch(Params params) {
//        开启分页查询
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        // 接下来会自动按照当前开启的分页设置来查询
        List<User> list = userDao.findBySearch(params);
        return PageInfo.of(list);
    }


    public void adminDeleteByID(Integer userId) {
        userDao.adminDeleteByID(userId);
    }

    public void userSaveOrUpdate(User user) {
        if (user.getUserId() == null) {
            System.out.println("userSaveOrUpdate方法里传入的为空ID");
            userDao.insertUser(user);
        } else {
            userDao.updateUser(user);
        }
    }


    public Integer findUserByBookingID(Integer bookingID) {
        return userDao.findUserByBookingID(bookingID);
    }

    public void updateUserPoints(Integer userId, int pointsToAdd) {
        User user = userDao.findUserByUserID(userId);
        System.out.println(user.toString());
        user.setPoints(user.getPoints() + pointsToAdd);

        // 判断是否达到vip标准
        if(user.getPoints() >= 100 && user.getUserType() != "vip"){
            user.setUserType("vip");
        }
        userDao.saveUser(user);
    }

    public User findStatus(Integer userId) {
        return userDao.findStatus(userId);
    }
}
