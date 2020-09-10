package com.atguigu.test;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null, "dch02", "admin", "dch02@123.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null, "dch02", "admin", null)));
    }

    @Test
    public void existsUsername() {
        if(userService.existsUsername("dch02")){
            System.out.println("username exists");
        }else {
            System.out.println("username available");
        }
    }
}