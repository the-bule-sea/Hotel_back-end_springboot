package com.hu.springboot_demo2.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hu.springboot_demo2.dao.BookDao;
import com.hu.springboot_demo2.dao.RoomDao;
import com.hu.springboot_demo2.entity.*;
import com.hu.springboot_demo2.exception.CustomException;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


@Service
public class BookService {

    @Autowired
    private BookDao bookDao;
    @Autowired
    private RoomDao roomDao;

    @Transactional
    public void Bookroom(Book bookinfo){
        if(bookinfo.getBookName() == null || "".equals(bookinfo.getBookName())){
            throw new CustomException("请输入用户名再试");
        }else if(bookinfo.getIdNumber() == null || "".equals(bookinfo.getIdNumber())){
            throw new CustomException("请输入身份证号再试");
        } else if (!isValidIdNumber(bookinfo.getIdNumber())) {
            throw new CustomException("请输入正确格式的身份证再试");
        }
        System.out.println("BookService接受到的内容"+bookinfo);
        Room room = roomDao.findByRoomID(bookinfo.getRoomID());
        System.out.println("BookService查询到的内容"+room);
        if(room == null){
            throw new CustomException("无此房间");
        }
        else if(room.getRemainder() <= 0){
            throw new CustomException("房间数不够了");
        }
        else{
            bookDao.bookRoom(bookinfo);
        }




    }

    /*正则表达式字符串中的末尾部分不应包含斜杠/i。
    这是正则表达式的标志，应在编译正则表达式时指定，而不是作为字符串的一部分。
    Pattern.compile方法不能接受/i作为字符串的一部分。
    你需要在编译模式时使用Pattern.CASE_INSENSITIVE标志。*/
    /*private boolean isValidIdNumber(String idNumber){
        String regex = "^(\\d{6}(18|19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[1-2]\\d|3[0-1])\\d{3}(\\d|X))$/i";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(idNumber).matches();
    }*/
    private boolean isValidIdNumber(String idNumber){
        String regex = "^(\\d{6}(18|19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[1-2]\\d|3[0-1])\\d{3}(\\d|X))$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(idNumber).matches();
    }



    public List<BookDetail> findBookDetailByID(Integer userId) {
        List<BookDetail> bookDetailList = bookDao.findBookByID(userId);
        return bookDetailList;
    }

    public void cancelbook(Integer bookingID) {
        bookDao.cancelbook(bookingID);
    }

    public void paybook(Integer bookingID) {
        bookDao.paybook(bookingID);
    }

    //后台订单信息--视图版
    /*public PageInfo<BookWithHotelWithUser> AdminfindRoomBySearch(Map<String, Object> params) {
        List<BookWithHotelWithUser> list = bookDao.AdminfindRoomBySearch(params);
        return PageInfo.of(list);
    }*/

    // 后台订单信息--存储过程版,不是哥们
    public PageInfo<BookWithHotelWithUser> AdminfindRoomBySearch(Map<String, Object> params) {
        String userName = (String) params.get("userName");
        String bookName = (String) params.get("bookName");
        String bookStatus = (String) params.get("bookStatus");
        List<BookWithHotelWithUser> list = bookDao.callGetUserOrdersByParams_PROC(userName, bookName, bookStatus);
        return PageInfo.of(list);
    }

    public List<OrderStatusData> getOrderData() {
        List<OrderStatusData> orderStatusDataList = bookDao.getOrderData();
        return orderStatusDataList;
    }

    public List<RoomOccupancyData> getRoomOccupancyRate(LocalDateTime startDate, LocalDateTime endDate) {
        List<RoomOccupancyData> list =  bookDao.getRoomOccupancyRate(startDate, endDate);
        System.out.println(list);
        return list;
    }
}
