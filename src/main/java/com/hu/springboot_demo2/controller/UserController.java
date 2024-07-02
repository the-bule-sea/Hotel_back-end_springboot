package com.hu.springboot_demo2.controller;


import com.github.pagehelper.PageInfo;
import com.hu.springboot_demo2.common.Result;
import com.hu.springboot_demo2.dao.HotelDao;
import com.hu.springboot_demo2.entity.Hotel;
import com.hu.springboot_demo2.entity.HotelParams;
import com.hu.springboot_demo2.entity.User;
import com.hu.springboot_demo2.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//controller是后台接口的入口
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
//    /**
//     * controller里的一个方法，就是我们web项目的一个接口的入口
//     * 可以在这个方法上加个url
//     * 也可指定请求方法
//     * @return
//     */
    @Resource
    private UserService userService;

    //登录
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        System.out.println("UserController后端收到前端:"+user.toString());
        User loginUser = userService.login(user);
        System.out.println(loginUser.toString());
        return Result.success(loginUser);
    }

    //注册
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        System.out.println(user.toString());
        User registerUser = userService.register(user);
        return Result.success(registerUser);
    }


    @GetMapping("/search")
    public Result findBySearch(HotelParams hotelparams){
        System.out.println("UserController后端收到前端:"+hotelparams.toString());
        PageInfo<Hotel> info = userService.findBySearch(hotelparams);
//        System.out.println("后端返回给前端:"+info.toString());
        return Result.success(info);
    }

    @GetMapping("/userStatus/{userId}")
    public Result findStatus(@PathVariable Integer userId){
        User user = userService.findStatus(userId);
        return Result.success(user);
    }
}
