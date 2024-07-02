package com.hu.springboot_demo2.entity;

public class RoomWithHotel {
    private String hotelName;
    private Integer hotelID;
    private Integer roomID;
    private double price;
    private String roomTypeName;
    private Integer Remainder;

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
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

    public Integer getRemainder() {
        return Remainder;
    }

    public void setRemainder(Integer remainder) {
        Remainder = remainder;
    }
}
