package com.hu.springboot_demo2.dao;

import com.hu.springboot_demo2.entity.CityHotelData;
import com.hu.springboot_demo2.entity.Hotel;
import com.hu.springboot_demo2.entity.HotelParams;
import com.hu.springboot_demo2.entity.Params;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface HotelDao extends Mapper<Hotel> {

    // 后台页面
    // 本来应该再写个HotelParams2的，但是时间来不及了，用了使用@RequestParam Map<String, Object> params，Spring MVC会将URL请求参数自动转换成一个键值对的Map，并将其注入到params中。
    // 一开始用的params.hotelName，！！报错,
    // 原因：在DAO层使用MyBatis时，Map中的每个键值对会被直接作为参数传递。因此在SQL查询语句中可以直接引用Map中的键来进行参数替换，而不需要通过params前缀。
    // findHotelBySearch方法接收到的参数是一个Map，而不是一个包含params字段的对象。因此，引用参数时应该直接使用Map的键名
    @Select("select HotelID,HotelName,City,HotelDescription from hotelinfo WHERE hotelinfo.HotelName LIKE CONCAT('%', #{hotelName}, '%') AND City LIKE CONCAT('%', #{city}, '%')")
    List<Hotel> AdminfindHotelBySearch(Map<String, Object> params);

    // 注意了，@Param("hotelParams") 中的 hotelParams 需要和接口中的参数名一致，一开始写成小写的hotelparams了，导致报错NULL
    // 一直搞不定，HotelDescription的返回值一直为NULL。使用resultMap明确映射后终于有返回值了
    /*@Results({
            @Result(property = "hotelID", column = "hotelID"),
            @Result(property = "hotelName", column = "HotelName"),
            @Result(property = "city", column = "city"),
            @Result(property = "hotelDes", column = "HotelDescription")
    })*/

    @Select("select hotelID, HotelName, city, HotelDescription from hotelinfo_view where city LIKE CONCAT('%', #{hotelParams.city}, '%')")
    List<Hotel> findByCity(@Param("hotelParams") HotelParams hotelParams);

    @Select("select hotelID, HotelName, city, HotelDescription from hotelinfo_view where HotelName like CONCAT('%', #{hotelParams.aboutname}, '%')")
    List<Hotel> findByName(@Param("hotelParams")HotelParams hotelParams);

    // 后台查看酒店界面--删除功能
    @Delete("DELETE FROM hotelinfo where HotelID = #{hotelID}")
    void adminDeleteHotelByID(Integer hotelID);

    // 后台查看酒店--添加新酒店功能
    @Insert("INSERT INTO hotelinfo (HotelName, city, Hoteldescription) VALUES (#{hotelName}, #{city}, #{hoteldescription})")
    void insertHotel(Hotel hotel);

    // 后台查看酒店--更新酒店功能
    @Update("UPDATE hotelinfo SET HotelName=#{hotelName}, city=#{city}, HotelDescription=#{hotelDescription} WHERE HotelID=#{hotelID}")
    void updateHotel(Hotel hotel);

    // 后台查看酒店--是否存在酒店
    @Select("SELECT hotelinfo.HotelID FROM hotelinfo where HotelName=#{hotelName}")
    Hotel findByHotelName(String hotelName);

    // 后台echart图--酒店与城市数据
    @Select("SELECT hotelinfo.City AS name, COUNT(*) AS value FROM hotelinfo GROUP BY City")
    List<CityHotelData> getCityHotelData();
}
