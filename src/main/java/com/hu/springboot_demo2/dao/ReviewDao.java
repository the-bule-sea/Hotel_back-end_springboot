package com.hu.springboot_demo2.dao;

import com.hu.springboot_demo2.entity.Review;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ReviewDao extends Mapper<Review> {

    // 添加评论
    @Insert("INSERT INTO review (UserID, BookingID, Comment, Rating)" +
    "VALUES (#{userID}, #{bookingID}, #{comment}, #{rating})")
    void addreviews(Review review);

    // 获取评论
    @Select("SELECT review.Comment, review.Rating from review where BookingID = #{bookingID}")
    Review getreviews(@PathVariable("bookingID")Integer bookingID);

}
