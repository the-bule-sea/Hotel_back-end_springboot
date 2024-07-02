package com.hu.springboot_demo2.controller;

import com.hu.springboot_demo2.common.Result;
import com.hu.springboot_demo2.entity.Book;
import com.hu.springboot_demo2.entity.BookDetail;
import com.hu.springboot_demo2.entity.Review;
import com.hu.springboot_demo2.service.BookService;
import com.hu.springboot_demo2.service.ReviewService;
import com.hu.springboot_demo2.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {
    @Resource
    private BookService bookService;

    @Resource
    private ReviewService reviewService;

    @Resource
    private UserService userService;

    // 订购
    @PostMapping("/room")
    public Result bookroom(@RequestBody Book book){
        System.out.println("BookController收到" + book.toString());
        bookService.Bookroom(book);
        return Result.success();
    }

    // 获取订单
    @GetMapping("/{userId}")
    public Result historybook(@PathVariable Integer userId){
        List<BookDetail> bookDetailList = bookService.findBookDetailByID(userId);
        System.out.println("BookController返回的"+bookDetailList);
        return Result.success(bookDetailList);
    }

    // 取消订单
    @PostMapping("/cancelBooking/{bookingID}")
    public Result cancelbook(@PathVariable Integer bookingID){
        bookService.cancelbook(bookingID);
        return Result.success();
    }

    // 付款（就是更新状态）
    @PostMapping("/payBooking/{bookingID}")
    public Result paybook(@PathVariable Integer bookingID) {
        bookService.paybook(bookingID);
        // 通过bookingID反查user
        Integer userId = userService.findUserByBookingID(bookingID);
        // 添加积分
        userService.updateUserPoints(userId, 10);
        return Result.success();
    }

    @PostMapping("/reviews")
    public Result addreviews(@RequestBody Review review){
        reviewService.addreviews(review);
        return Result.success();
    }

    @GetMapping("/getviews/{bookingID}")
    public Result getreviews(@PathVariable Integer bookingID){
        Review review = reviewService.getreviews(bookingID);
        return Result.success(review);
    }

}
