package com.atguigu.service.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.pojo.*;
import com.atguigu.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //orderId is unique
        String orderId = System.currentTimeMillis() + "" + userId;
        //create an order obj
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(),0, userId);
        //save order
        orderDao.saveOrder(order);

        //traverse every item in cart, transfer it to order item in db
        for(Map.Entry<Integer, CartItem>entry : cart.getItems().entrySet()){
            //get every good item in cart
            CartItem cartItem = entry.getValue();
            //transfer it to every order item
            OrderItem orderItem = new OrderItem(null, cartItem.getName(),cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId, 0, cartItem.getOwner());
            //save order item to DB
            orderItemDao.saveOrderItem(orderItem);

            //refresh sales and stock
            //Book book = bookDao.queryBookById(cartItem.getId());
            //book.setSales(book.getSales() + cartItem.getCount());
            //book.setStock(book.getStock() - cartItem.getCount());
            //bookDao.updateBook(book);

        }

        //清空购物车
        //cart.clear();

        return orderId;
    }

    @Override
    public List<Order> showAllOrders() {
        return orderDao.queryOrder();
    }

    @Override
    public List<Order> showMyOrders(Integer userId) {
        return orderDao.queryOrderByUserId(userId);
    }

    @Override
    public List<OrderItem> showOrderDetails(String orderId) {
        return orderItemDao.queryOrderItemsByOrderId(orderId);
    }

    @Override
    public List<OrderItem> showMySoldItems(String owner) {
        return orderItemDao.queryOrderItemsByOwner(owner);
    }

    @Override
    public int sendOrderItem(Integer itemId) {
        orderItemDao.changeOrderItemStatus(itemId, 1);
        return 1;
    }

    @Override
    public int receiveOrderItem(Integer itemId) {
        orderItemDao.changeOrderItemStatus(itemId, 2);
        return 2;
    }

    @Override
    public int checkOrderProcess(String orderId) {
        for(OrderItem orderItem : orderItemDao.queryOrderItemsByOrderId(orderId)){
            if(orderItem.getStatus() == 0) return 0;
        }
        orderDao.changeOrderStatus(orderId, 1);
        return 1;
    }

    @Override
    public void deleteOrder(String orderId) {
        orderItemDao.deleteOrderItemsByOrderId(orderId);
        orderDao.deleteOrderByOrderId(orderId);
    }


}
