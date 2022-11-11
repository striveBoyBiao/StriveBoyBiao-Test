package com.zizhuling.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.zizhuling"})
@MapperScan("com.zizhuling.**.dao")
public class BlogApplication {

    public static void main(String[] args) {
         SpringApplication.run(BlogApplication.class, args);
    }

}
