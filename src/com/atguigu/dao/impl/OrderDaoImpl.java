package com.atguigu.dao.impl;

import com.atguigu.dao.OrderDao;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Order;

import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {

        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values (?, ?, ?, ?, ?)";

        return update(sql,order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getUserId());
    }

    @Override
    public List<Order> queryOrder() {
        String sql = "select `order_id` as orderId,`create_time` as createTime,`price`,`status`,`user_id` as userId from t_order";
        return queryForList(Order.class, sql);
    }

    @Override
    public int changeOrderStatus(String orderId, Integer status) {
        String sql = "update t_order set `status`=? where order_id = ?";
        return update(sql, status, orderId);
    }

    @Override
    public List<Order> queryOrderByUserId(Integer userId) {
        String sql = "select `order_id` as orderId,`create_time` as createTime,`price`,`status`,`user_id` as userId from t_order where user_id = ?";
        return queryForList(Order.class, sql, userId);
    }

    @Override
    public int deleteOrderByOrderId(String orderId) {
        String sql = "delete from t_order where order_id = ?";
        return update(sql, orderId);
    }


}
