package com.hu.springboot_demo2.entity;

import java.time.LocalDateTime;

public class BookDetail {
    private Integer bookingID;
    private Integer roomID;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private String bookStatus;
    private String idNumber;
    private String bookName;
    private String hotelName;
    private double price;
    private String roomTypeName;


    public Integer getBookingID() {
        return bookingID;
    }

    public void setBookingID(Integer bookingID) {
        this.bookingID = bookingID;
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

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    @Override
    public String toString() {
        return "BookDetail{" +
                "bookingID=" + bookingID +
                ", roomID=" + roomID +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", bookStatus='" + bookStatus + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", bookName='" + bookName + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", price=" + price +
                ", roomTypeName='" + roomTypeName + '\'' +
                '}';
    }
}
