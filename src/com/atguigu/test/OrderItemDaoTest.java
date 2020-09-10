package com.atguigu.test;

import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderItemDaoTest {

    private OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Test
    public void saveOrderItem() {

        orderItemDao.saveOrderItem(new OrderItem(null, "N20 Logitech mouse", 1, new BigDecimal(12), new BigDecimal(12), "12345", 0, "admin"));
        orderItemDao.saveOrderItem(new OrderItem(null, "Instant pot", 1, new BigDecimal(30), new BigDecimal(30), "12345",  0, "admin"));
        orderItemDao.saveOrderItem(new OrderItem(null, "Body fat scale", 1, new BigDecimal(15), new BigDecimal(15), "12345",  0, "admin"));

    }

    @Test
    public void queryOrderItemsByOrderId() {

        for(OrderItem orderItem : orderItemDao.queryOrderItemsByOrderId("12345")){
            System.out.println(orderItem);
        }
    }

    @Test
    public void queryOrderItemsByOwner() {

        for(OrderItem orderItem : orderItemDao.queryOrderItemsByOwner("admin")){
            System.out.println(orderItem);
        }
    }

    @Test
    public void changeOrderItemStatus() {

        orderItemDao.changeOrderItemStatus(6, 2);
    }

    @Test
    public void deleteOrderItemsByOrderId() {

        orderItemDao.deleteOrderItemsByOrderId("12345");
    }
}