package com.hu.springboot_demo2.service;

import com.hu.springboot_demo2.dao.ReviewDao;
import com.hu.springboot_demo2.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    // 又忘记加Service了
    @Autowired
    private ReviewDao reviewDao;

    public void addreviews(Review review) {
        reviewDao.addreviews(review);
    }

    public Review getreviews(Integer bookingID) {
        Review review = reviewDao.getreviews(bookingID);
        return review;
    }
}
