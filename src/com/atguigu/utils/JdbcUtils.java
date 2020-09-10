package com.atguigu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static DruidDataSource dataSource;

    static {
        try {
            Properties properties = new Properties();
            //read jdbc.properties
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //load data from stream
            properties.load(inputStream);
            //generate data connection pool
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

            /**
             * 测试代码
             * System.out.println(dataSource.getConnection());
             */


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 用main方法来测试 上面的 dataSource.getConnection() 是否执行成功
     * public static void main(String[] args) {} 测试代码
     */


    /**
     * //druid needs a config file to use
     * get the connection to db from pool
     * @return return null means fail connection<br/> value means succeed
     */
    public static Connection getConnection(){

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    /**
     * close connection return to the pool
     * @param conn
     */
    public static void close(Connection conn){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    //每一步都要测试！！！
}
