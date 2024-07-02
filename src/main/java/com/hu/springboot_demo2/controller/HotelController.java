package com.hu.springboot_demo2.controller;

import com.hu.springboot_demo2.common.Result;
import com.hu.springboot_demo2.entity.Room;
import com.hu.springboot_demo2.service.HotelService;
import jakarta.annotation.Resource;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Resource
    private HotelService hotelService;

    @GetMapping("/{hotelID}")
    public Result findByID(@PathVariable Integer hotelID){
        System.out.println("HotelController Received hotelID: " + hotelID);
        List<Room> list = hotelService.findByID(hotelID);
        return Result.success(list);
    }
}
