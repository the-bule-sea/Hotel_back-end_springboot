package com.hu.springboot_demo2.service;

import com.github.pagehelper.PageInfo;
import com.hu.springboot_demo2.dao.HotelDao;
import com.hu.springboot_demo2.dao.RoomDao;
import com.hu.springboot_demo2.entity.Hotel;
import com.hu.springboot_demo2.entity.Room;
import com.hu.springboot_demo2.entity.RoomWithHotel;
import com.hu.springboot_demo2.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoomService {

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private HotelDao hotelDao;


    public PageInfo<RoomWithHotel> AdminfindRoomBySearch(Map<String, Object> params) {
        List<RoomWithHotel> list = roomDao.AdminfindRoomBySearch(params);
        System.out.println("RoomService后台页面处理后" + list);
        return PageInfo.of(list);
    }

    public void adminDeleteRoomByID(Integer roomID) {
        roomDao.adminDeleteRoomByID(roomID);
    }

    public void roomSaveOrUpdate(RoomWithHotel roomWithHotel) {
        // 1. 先判断传回来的酒店名是否存在
         /*7.1,666,这里刚才springboot又报错了，因为和adminDao中的方法重名了
         7.1 缝缝补补，hotel中带hotelID*/
        Hotel hotel = hotelDao.findByHotelName(roomWithHotel.getHotelName());
        if(hotel == null){
            throw new CustomException("此酒店不存在");
        }
        // 2. 存在，判断是要编辑还是更新
        if(roomWithHotel.getRoomID() == null){
            // 赋予一个hotelID，从hotel上面中来的
            roomWithHotel.setHotelID(hotel.getHotelID());
            roomDao.insertRoom(roomWithHotel);
        }else{
            roomDao.updateRoom(roomWithHotel);
        }


    }
}
