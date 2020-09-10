package com.atguigu.dao;

import com.atguigu.pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {

    public int saveOrderItem(OrderItem orderItem);

    public List<OrderItem> queryOrderItemsByOrderId(String orderId);

    public List<OrderItem> queryOrderItemsByOwner(String owner);

    public int changeOrderItemStatus(Integer id, Integer status);

    public int deleteOrderItemsByOrderId(String orderId);

}
