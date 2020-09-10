package com.atguigu.test;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceTest {

    private OrderService orderService = new OrderServiceImpl();

    @Test
    public void createOrder() {

        Cart cart = new Cart();

        cart.addItem(new CartItem(1, "Web Design", 1, new BigDecimal(999), new BigDecimal(999), "admin"));
        cart.addItem(new CartItem(1, "Web Design", 1, new BigDecimal(999), new BigDecimal(999), "admin"));
        cart.addItem(new CartItem(2, "Android App", 1, new BigDecimal(88), new BigDecimal(88), "admin"));

        System.out.println("the order id is " + orderService.createOrder(cart, 1));

    }

    @Test
    public void showAllOrders() {
        for(Order order : orderService.showAllOrders()){
            System.out.println(order);
        }
    }

    @Test
    public void showMyOrders() {
        for(Order order : orderService.showMyOrders(1)){
            System.out.println(order);
        }
    }

    @Test
    public void showOrderDetails() {
        for(OrderItem orderItem : orderService.showOrderDetails("12345")){
            System.out.println(orderItem);
        }
    }

    @Test
    public void showMySoldItems() {
        for(OrderItem orderItem : orderService.showMySoldItems("admin")){
            System.out.println(orderItem);
        }
    }

    @Test
    public void sendOrderItem() {
        System.out.println(orderService.sendOrderItem(8));
    }

    @Test
    public void receiveOrderItem() {
        System.out.println(orderService.receiveOrderItem (8));
    }

    @Test
    public void checkOrderProcess() {
        System.out.println(orderService.checkOrderProcess("12345"));
    }

    @Test
    public void deleteOrder() {
        orderService.deleteOrder("12345");
    }
}