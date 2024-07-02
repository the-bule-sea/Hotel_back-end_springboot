package com.hu.springboot_demo2.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hu.springboot_demo2.dao.AdminDao;
import com.hu.springboot_demo2.entity.Admin;
import com.hu.springboot_demo2.entity.Params;
import com.hu.springboot_demo2.entity.User;
import com.hu.springboot_demo2.exception.CustomException;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Service
public class AdminService {

    @Autowired
    private AdminDao adminDao;

//    public List<Admin> getAll() {
////        return userDao.getUser();
////        3、mybatis里的方法
//        return adminDao.selectAll();
//    }

    public void add(Admin admin) {
        // 0. 进行非空判断
        if(admin.getAdminName() == null || "".equals(admin.getAdminName())){
            throw new CustomException("用户名不能为空");
        }
        // 1. 进行重复性判断，同一名字的管理员不允许重复新建：只要用用户名去数据库查询
        Admin user = adminDao.findByName(admin.getAdminName());
        if(user != null){
            // 2. 重复了，提示前台不允许新增了
            throw new CustomException("该用户名已存在，请勿重复添加");

        }
        // 初始化一个密码
        if(admin.getPassword() == null || admin.getPassword().trim().equals("") ){
            admin.setPassword("123456");
        }
        adminDao.insertSelective(admin);
    }

    public void update(Admin admin) {
        adminDao.updateByPrimaryKeySelective(admin);
    }

    public void delete(Integer id) {
        adminDao.deleteByPrimaryKey(id);
    }

    public Admin login(Admin admin) {
        if(admin.getAdminName() == null || "".equals(admin.getAdminName())){
            throw new CustomException("用户名不能为空");
        }
        // 1.5 进行密码为空判断
        if(admin.getPassword() == null || "".equals(admin.getPassword())){
            throw new CustomException("密码不能为空");
        }

        // 2. 查询数据库
        Admin adminfind = adminDao.findByNameAndPassword(admin.getAdminName(), admin.getPassword());
        if(adminfind == null){
            //说明密码或者账户错误
            throw new CustomException("用户名或密码错误");
        }
        return adminfind;
    }

    public PageInfo<Admin> AdminfindAdminBySearch(@RequestParam Map<String, Object> params) {
        // 接下来会自动按照当前开启的分页设置来查询
        List<Admin> list = adminDao.AdminfindAdminBySearch(params);
        return PageInfo.of(list);
    }


    //已转移至UserDao层
    /*public Admin login(Admin admin) {
        // 1. 进行非空判断
        if(admin.getName() == null || "".equals(admin.getName())){
            throw new CustomException("用户名不能为空");
        }
        // 1.5 进行密码为空判断
        if(admin.getPassword() == null || "".equals(admin.getPassword())){
            throw new CustomException("密码不能为空");
        }
        // 2. 查询数据库
        Admin user = adminDao.findByNameAndPassword(admin.getName(), admin.getPassword());
        if(user == null){
            //说明密码或者账户错误
            throw new CustomException("用户名或密码错误");
        }
        return user;
    }*/
}
