package com.hu.springboot_demo2.exception;

import com.hu.springboot_demo2.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages="com.hu.springboot_demo2.controller")
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //统一异常处理@ExceptionHandler,主要用于Exception
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(HttpServletRequest request, Exception e){
        log.error("异常信息：",e);
        return Result.error("系统异常");
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Result customError(HttpServletRequest request,  CustomException e){
        //response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return Result.error(e.getMsg());
    }
}

