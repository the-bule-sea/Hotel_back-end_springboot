package com.hu.springboot_demo2.entity;

import javax.persistence.*;

@Table(name = "review")
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewID;

    @Column(name = "UserID")
    private Integer userID;

    @Column(name = "BookingID")
    private Integer bookingID;

    @Column(name = "Comment")
    private String comment;

    @Column(name = "Rating")
    private Integer rating;

    public Integer getReviewID() {
        return reviewID;
    }

    public void setReviewID(Integer reviewID) {
        this.reviewID = reviewID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getBookingID() {
        return bookingID;
    }

    public void setBookingID(Integer bookingID) {
        this.bookingID = bookingID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewID=" + reviewID +
                ", userID=" + userID +
                ", bookingID=" + bookingID +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                '}';
    }
}
