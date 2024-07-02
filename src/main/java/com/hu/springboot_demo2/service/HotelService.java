package com.hu.springboot_demo2.service;

import com.github.pagehelper.PageInfo;
import com.hu.springboot_demo2.dao.HotelDao;
import com.hu.springboot_demo2.dao.RoomDao;
import com.hu.springboot_demo2.entity.*;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

// 忘记写注解了，导致springboot注入失败（6.26）
@Service
public class HotelService {

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private HotelDao hotelDao;



    public List<Room> findByID(Integer hotelID) {
        return roomDao.findByID(hotelID);
    }

    public PageInfo<Hotel> AdminfindHotelBySearch(Map<String, Object> params) {
        List<Hotel> list = hotelDao.AdminfindHotelBySearch(params);
        System.out.println("HotelService后台页面处理后" + list);
        return PageInfo.of(list);
    }

    public void adminDeleteHotelByID(Integer hotelID) {
        hotelDao.adminDeleteHotelByID(hotelID);
    }

    public void hotelSaveOrUpdate(Hotel hotel) {
        if(hotel.getHotelID() == null){
            hotelDao.insertHotel(hotel);
        }else{
            hotelDao.updateHotel(hotel);
        }
    }

    public List<CityHotelData> getCityHotelData() {
        List<CityHotelData> cityHotelDataList = hotelDao.getCityHotelData();
        return cityHotelDataList;
    }
}
