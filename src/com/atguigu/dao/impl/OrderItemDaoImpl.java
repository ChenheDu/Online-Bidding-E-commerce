package com.atguigu.dao.impl;

import com.atguigu.dao.OrderItemDao;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;

import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {

    @Override
    public int saveOrderItem(OrderItem orderItem) {

        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`,`status`,`owner`) values (?, ?, ?, ?, ?, ?, ?)";

        return update(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId(), orderItem.getStatus(), orderItem.getOwner());
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
        String sql = "select `id`,`name`,`count`,`price`,`total_price` as totalPrice,`order_id` as orderId,`status`,`owner` from t_order_item where order_id = ?";
        return queryForList(OrderItem.class, sql, orderId);
    }

    @Override
    public List<OrderItem> queryOrderItemsByOwner(String owner) {
        String sql = "select `id`,`name`,`count`,`price`,`total_price` as totalPrice,`order_id` as orderId,`status`,`owner` from t_order_item where owner = ?";
        return queryForList(OrderItem.class, sql, owner);
    }

    @Override
    public int changeOrderItemStatus(Integer id, Integer status) {
        String sql = "update t_order_item set `status`=? where id = ?";
        return update(sql, status, id);
    }

    @Override
    public int deleteOrderItemsByOrderId(String orderId) {
        String sql = "delete from t_order_item where order_id = ?";
        return update(sql, orderId);
    }
}
