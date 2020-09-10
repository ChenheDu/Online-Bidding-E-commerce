package com.atguigu.service;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;

import java.util.List;

public interface OrderService {

    public String createOrder(Cart cart, Integer userId);

    public List<Order> showAllOrders();

    public List<Order> showMyOrders(Integer userId);

    public List<OrderItem> showOrderDetails(String orderId);

    public List<OrderItem> showMySoldItems(String owner);

    public int sendOrderItem(Integer itemId);

    public int receiveOrderItem(Integer itemId);

    public int checkOrderProcess(String orderId);

    public void deleteOrder(String orderId);

}
