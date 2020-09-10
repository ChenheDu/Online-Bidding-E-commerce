package com.atguigu.dao;

import com.atguigu.pojo.User;

public interface UserDao {


    /**
     * 根据用户名查询用户信息
     * @param username
     * @return  返回null means 没有该用户，反之亦然
     */
    public User queryUserByUsername(String username);

    /**
     * 根据用户名和密码查询用户信息
     * @param username
     * @param password
     * @return  返回null means 用户名或密码错误，反之亦然
     */
    public User queryUserByUsernameAndPassword(String username, String password);

    /**
     * 保存用户信息
     * @param user
     * @return  return -1 means operation failed, 其他事sql语句影响的行数
     */
    public int saveUser(User user);

}
