package com.hu.springboot_demo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.hu.springboot_demo2.dao")
public class SpringbootDemo2Application {


    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemo2Application.class, args);
    }

}
