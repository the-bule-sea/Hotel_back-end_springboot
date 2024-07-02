package com.hu.springboot_demo2.entity;


import javax.persistence.*;

@Table(name = "roominfo")
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roomID;

    @Column(name = "HotelID")
    private Integer hotelID;

    @Column(name = "price")
    private double price;

    @Column(name = "RoomTypeName")
    private String roomTypeName;

    @Column(name = "Remainder")
    private Integer remainder;


    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    public Integer getHotelID() {
        return hotelID;
    }

    public void setHotelID(Integer hotelID) {
        this.hotelID = hotelID;
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
        return remainder;
    }

    public void setRemainder(Integer remainder) {
        this.remainder = remainder;
    }


    @Override
    public String toString() {
        return "Room{" +
                "roomID=" + roomID +
                ", hotelID=" + hotelID +
                ", price=" + price +
                ", roomTypeName='" + roomTypeName + '\'' +
                ", remainder=" + remainder +
                '}';
    }
}
