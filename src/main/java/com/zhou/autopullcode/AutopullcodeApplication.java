package com.zhou.autopullcode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhou.autopullcode.dao")
public class AutopullcodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutopullcodeApplication.class, args);
    }
}
