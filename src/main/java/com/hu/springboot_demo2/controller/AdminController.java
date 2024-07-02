package com.hu.springboot_demo2.controller;

import com.github.pagehelper.PageInfo;
import com.hu.springboot_demo2.common.Result;
import com.hu.springboot_demo2.entity.*;
import com.hu.springboot_demo2.service.*;
import jakarta.annotation.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

//controller层是后台接口的入口
@CrossOrigin//跨域处理
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @Resource
    private UserService userService;

    @Resource
    private HotelService hotelService;

    @Resource
    private RoomService roomService;

    @Resource
    private BookService bookService;

//    /**
//     * controller里的一个方法，就是我们web项目的一个接口的入口
//     * 可以在这个方法上加个url
//     * 也可指定请求方法
//     * @return
//     */


    //转移到UserController层
    /*@PostMapping("/login")
    public Result login(@RequestBody Admin admin){
        Admin loginUser = adminService.login(admin);
        return Result.success(loginUser);
    }*/

    //转移到UserController层
/*    @PostMapping("/register")
    public Result register(@RequestBody Admin admin){
        adminService.add(admin);//复用add
        return Result.success();
    }*/

    @PostMapping("/login")
    public Result login(@RequestBody Admin admin){
        Admin loginUser = adminService.login(admin);
        return Result.success(loginUser);
    }

    @PostMapping
    public Result addAdmin(@RequestBody Admin admin){
        if(admin.getId() == null){
            adminService.add(admin);
        }else{
            adminService.update(admin);
        }
        return Result.success();
    }


    @GetMapping("/search")
    public Result findBySearch(Params params){
        PageInfo<User> info = userService.findBySearch(params);
        return Result.success(info);
    }

    // 后台管理员信息
    @GetMapping("/searchAdmin")
    public Result adminfindAdminBySearch(@RequestParam Map<String, Object> params){
        PageInfo<Admin> info = adminService.AdminfindAdminBySearch(params);
        return Result.success(info);
    }

    // 后台酒店信息
    @GetMapping("/searchHotelinfo")
    // 实在不想定义第二个了--6.30上午
    public Result adminfindHotelBySearch(@RequestParam Map<String, Object> params){
        System.out.println(params);
        PageInfo<Hotel> info = hotelService.AdminfindHotelBySearch(params);
        return Result.success(info);
    }

    // 后台房间信息--视图版
    @GetMapping("/searchRoominfo")
    // 没办法，还是得定义返回值--6.30下午
    public Result adminfindRoomBySearch(@RequestParam Map<String, Object> params){

        PageInfo<RoomWithHotel> info = roomService.AdminfindRoomBySearch(params);
        return Result.success(info);
    }

    // 后台订单信息--视图版+存储过程版
    @GetMapping("/searchBookinfo")
    public Result adminfindBookBySearch(@RequestParam Map<String, Object> params){
        System.out.println("后台订单信息"+ params);
        PageInfo<BookWithHotelWithUser> info = bookService.AdminfindRoomBySearch(params);
        System.out.println("后台订单信息分页内容："+info);
        return Result.success(info);
    }



    @DeleteMapping("{id}")
    public Result delete1(@PathVariable Integer id){
        adminService.delete(id);
        return Result.success();
    }

    // 后台查看用户--删除用户
    @DeleteMapping("/deleteUser/{userId}")
    public Result deleteUser(@PathVariable Integer userId){
        userService.adminDeleteByID(userId);
        return Result.success();
    }

    // 后台查看用户--编辑或新增用户
    @PostMapping("/userSaveOrUpdate")
    public Result saveOrUpdate(@RequestBody User user) {
        userService.userSaveOrUpdate(user);
        System.out.println(user.toString());
        return Result.success();
    }

    // 后台查看酒店--删除酒店
    @DeleteMapping("/deleteHotel/{hotelID}")
    public Result deleteHotel(@PathVariable Integer hotelID){
        hotelService.adminDeleteHotelByID(hotelID);
        return Result.success();
    }

    // 后台查看酒店--编辑或新增酒店
    @PostMapping("/hotelSaveOrUpdate")
    public Result hotelSaveOrUpdate(@RequestBody Hotel hotel) {
        hotelService.hotelSaveOrUpdate(hotel);
        return Result.success();
    }

    // 后台查看房间--删除房间
    @DeleteMapping("/deleteRoom/{roomID}")
    public Result deleteRoom(@PathVariable Integer roomID){
        roomService.adminDeleteRoomByID(roomID);
        return Result.success();
    }

    // 后台查看房间--编辑或新增酒店
    @PostMapping("/roomSaveOrUpdate")
    public Result roomSaveOrUpdate(@RequestBody RoomWithHotel roomWithHotel){
        roomService.roomSaveOrUpdate(roomWithHotel);
        return Result.success();
    }

    // 后台管理界面--echart图之城市分布
    @GetMapping("/getHotelData")
    public Result getHotelDate(){
        List<CityHotelData> cityHotelDataList = hotelService.getCityHotelData();
        return Result.success(cityHotelDataList);
    }

    // 后台管理界面--echart图之订单状态
    @GetMapping("/getOrderData")
    public Result getOrderData(){
        List<OrderStatusData> orderStatusDataList = bookService.getOrderData();
        return Result.success(orderStatusDataList);
    }

    // 后台管理界面--echart图之入住率
    @GetMapping("/getRoomOccupancyRate")
    public Result getRoomOccupancyRate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)@RequestParam LocalDateTime endDate) {
        List<RoomOccupancyData> data = bookService.getRoomOccupancyRate(startDate, endDate);
        return Result.success(data);
    }
}
