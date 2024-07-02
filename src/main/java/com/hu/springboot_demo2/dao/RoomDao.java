package com.hu.springboot_demo2.dao;


import com.hu.springboot_demo2.entity.Room;
import com.hu.springboot_demo2.entity.RoomWithHotel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoomDao {

    @Select("select RoomID, price, RoomTypeName from roominfo where HotelID = #{hotelID}")
    List<Room> findByID(@Param("hotelID") Integer hotelID);

    /*6.28记录：提示roomID为空，因为把sql注解中的roomID写成大写了。
    提示getRemainder()为空，因为一开始只返回roomID
    写了这么久，还会犯这种错误*/
    @Select("select * from roominfo where RoomID = #{roomID}")
    Room findByRoomID(@Param("roomID") Integer roomID);

    // 后台房间信息--视图版
    @Select("select * FROM hotelinfo_roominfo_view where HotelName LIKE CONCAT('%',#{hotelName},'%') AND RoomTypeName LIKE CONCAT('%', #{roomTypeName}, '%')")
    List<RoomWithHotel> AdminfindRoomBySearch(Map<String, Object> params);

    // 后台查看房间--删除房间
    @Delete("DELETE FROM roominfo WHERE RoomID=#{roomID}")
    void adminDeleteRoomByID(Integer roomID);

    // 后台查看房间--新增房间
    @Insert("INSERT INTO roominfo (HotelID, Price, RoomTypeName, Remainder) VALUES (#{hotelID}, #{price}, #{roomTypeName}, #{remainder})")
    void insertRoom(RoomWithHotel roomWithHotel);

    // 后台查看房间--编辑房间
    @Update("UPDATE roominfo SET HotelID = #{hotelID}, Price = #{price}, RoomTypeName = #{roomTypeName}, Remainder = #{remainder} WHERE RoomID = #{roomID}")
    void updateRoom(RoomWithHotel roomWithHotel);
}
