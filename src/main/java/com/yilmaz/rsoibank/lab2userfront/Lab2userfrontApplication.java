package com.yilmaz.rsoibank.lab2userfront;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@ComponentScan({"com.yilmaz.rsoibank.lab2userfront.Dao","com.yilmaz.rsoibank.lab2userfront.UserService.UserServiceImpl"})
public class Lab2userfrontApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab2userfrontApplication.class, args);
    }
}
