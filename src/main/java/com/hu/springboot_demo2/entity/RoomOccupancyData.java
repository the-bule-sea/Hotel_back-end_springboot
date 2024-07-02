package com.hu.springboot_demo2.entity;

public class RoomOccupancyData {
    private String roomTypeName;
    private Double bookingCount;

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public Double getBookingCount() {
        return bookingCount;
    }

    public void setBookingCount(Double bookingCount) {
        this.bookingCount = bookingCount;
    }
}
