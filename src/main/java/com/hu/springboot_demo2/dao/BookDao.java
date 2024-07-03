package com.hu.springboot_demo2.dao;

import com.hu.springboot_demo2.entity.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import tk.mybatis.mapper.common.Mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface BookDao {

    // 处理订购
    @Insert("insert into bookinfo (UserID, HotelID, RoomID, CheckInDate, CheckOutDate, IDNumber, BookName)" +
    "VALUE (#{userID}, #{hotelID}, #{roomID}, #{checkInDate}, #{checkOutDate}, #{idNumber}, #{bookName})")
    void bookRoom(Book book);
    
    
    // 获取历史记录
    @Select("SELECT b.RoomID, b.BookingID , b.CheckInDate, b.CheckOutDate, b.BookStatus, b.IDNumber, b.BookName, " +
            "h.HotelName, r.price, r.roomTypeName " +
            "FROM bookinfo b " +
            "JOIN hotelinfo h ON b.HotelID = h.HotelID " +
            "JOIN roominfo r ON b.RoomID = r.RoomID AND b.HotelID = r.HotelID " +
            "WHERE b.UserID = #{userID}")
    List<BookDetail> findBookByID(@Param("userID") Integer userID);


    // @Delete("DELETE FROM bookinfo WHERE BookID = #{bookId}")
    // 取消订单功能，差点就delete了，
    @Update("UPDATE bookinfo SET BookStatus = '订单已取消' WHERE BookingID = #{bookingID}")
    void cancelbook(@PathVariable("bookingID") Integer bookingID);

    //付款
    @Update("UPDATE bookinfo SET BookStatus = '订单已支付' WHERE BookingID = #{bookingID}")
    void paybook(@PathVariable("bookingID") Integer bookingID);

    /*// 后台订单信息--视图版
    @Select("SELECT * FROM bookinfo_hotelinfo_user_view WHERE UserName LIKE CONCAT('%', #{userName}, '%') AND BookName LIKE CONCAT('%', #{bookName}, '%') AND BookStatus LIKE CONCAT('%', #{bookStatus}, '%')")
    List<BookWithHotelWithUser> AdminfindRoomBySearch(Map<String, Object> params);*/

    // 后台订单信息--存储过程版,不是哥们
    @Select("CALL GetUserOrdersByParams_PROC(#{userName}, #{bookName}, #{bookStatus})")
    List<BookWithHotelWithUser> callGetUserOrdersByParams_PROC(
            @Param("userName") String userName,
            @Param("bookName") String bookName,
            @Param("bookStatus") String bookStatus
    );

    // 后台echart图--订单状态
    @Select("SELECT bookinfo.BookStatus AS name, COUNT(*) AS value FROM bookinfo GROUP BY bookinfo.BookStatus")
    List<OrderStatusData> getOrderData();


    //
    @Select("CALL GetRoomRate_PROC(#{startDate}, #{endDate})")
    List<RoomOccupancyData> getRoomOccupancyRate(@Param("startDate")LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
