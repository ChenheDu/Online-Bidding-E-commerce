package com.atguigu.test;

import com.atguigu.dao.OrderDao;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.pojo.Order;
import org.junit.After;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderDaoTest {

    private OrderDao orderDao = new OrderDaoImpl();

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void saveOrder() {

        orderDao.saveOrder(new Order("12345", new Date(), new BigDecimal(100), 0, 1));
    }

    @Test
    public void queryOrder() {

        for(Order order : orderDao.queryOrder()){
            System.out.println(order);
        }
    }

    @Test
    public void changeOrderStatus() {

        orderDao.changeOrderStatus("12345", 1);
    }

    @Test
    public void queryOrderByUserId() {

        for(Order order : orderDao.queryOrderByUserId(1)){
            System.out.println(order);
        }
    }

    @Test
    public void deleteOrderByOrderId() {

        orderDao.deleteOrderByOrderId("12346");
    }
}