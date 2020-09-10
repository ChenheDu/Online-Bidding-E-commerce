package com.atguigu.test;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;
import org.junit.Test;

import java.nio.channels.DatagramChannel;

import static org.junit.Assert.*;

public class UserDaoTest {

    UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {
        if(userDao.queryUserByUsername("admin") == null){
            System.out.println("Username available");
        }else{
            System.out.println("Username already exists");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if(userDao.queryUserByUsernameAndPassword("admin", "admin") == null){
            System.out.println("wrong user name or password");
        }else{
            System.out.println("search successful");
        }
    }

    @Test
    public void saveUser() {


        System.out.println(userDao.saveUser(new User(null, "dch01", "admin", "dch@123.com")));
    }
}