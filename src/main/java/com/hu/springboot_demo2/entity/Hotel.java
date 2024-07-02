package com.hu.springboot_demo2.entity;

import javax.persistence.*;

@Table(name = "hotelinfo")
@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hotelID;

    @Column(name = "HotelName")
    private String hotelName;

    @Column(name = "city")
    private String city;

    // 震惊，居然必须得字段名相同(除开大小写)（java命名一般小写，我这里数据库字段名大写）
    // 或者可以在dao层中，明显映射
    @Column(name = "HotelDescription")
    private String hotelDescription;

    /*6.30 终于知道振中之前说的是啥意思了，
    前端接受到的数据，命名格式是按照get和set方法来的，
    怪不得前端一直接受到的是id，但是后端的返回又是hotelID*/
    public Integer getHotelID() {
        return hotelID;
    }

    public void setHotelID(Integer hotelID) {
        this.hotelID = hotelID;
    }
    /*public Integer getId() {
        return hotelID;
    }

    public void setId(Integer id) {
        this.hotelID = id;
    }*/

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHotelDescription() {
        return hotelDescription;
    }

    public void setHotelDescription(String hotelDescription) {
        this.hotelDescription = hotelDescription;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelID=" + hotelID +
                ", hotelName='" + hotelName + '\'' +
                ", city='" + city + '\'' +
                ", hotelDescription='" + hotelDescription + '\'' +
                '}';
    }


}
