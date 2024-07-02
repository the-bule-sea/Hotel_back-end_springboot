package com.hu.springboot_demo2.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "bookinfo")
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingID;

    @Column(name = "UserID")
    private Integer userID;

    @Column(name = "HotelID")
    private Integer hotelID;

    @Column(name = "RoomID")
    private Integer roomID;

    @Column(name = "BookName")
    private String bookName;

    @Column(name = "CheckInDate")
    private LocalDateTime checkInDate;

    @Column(name = "CheckOutDate")
    private LocalDateTime checkOutDate;

    @Column(name = "BookStatus")
    private String bookStatus;

    @Column(name = "IDNumber")
    private String idNumber;


    public Integer getBookingID() {
        return bookingID;
    }

    public void setBookingID(Integer bookingID) {
        this.bookingID = bookingID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getHotelID() {
        return hotelID;
    }

    public void setHotelID(Integer hotelID) {
        this.hotelID = hotelID;
    }

    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    public LocalDateTime getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDateTime checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDateTime getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDateTime checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }


    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookingID=" + bookingID +
                ", userID=" + userID +
                ", hotelID=" + hotelID +
                ", roomID=" + roomID +
                ", bookName='" + bookName + '\'' +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", bookStatus='" + bookStatus + '\'' +
                ", idNumber='" + idNumber + '\'' +
                '}';
    }
}
