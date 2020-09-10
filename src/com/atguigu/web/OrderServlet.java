package com.atguigu.web;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import com.atguigu.pojo.User;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();

    ///**
    // * create order
    // * @param req
    // * @param resp
    // * @throws ServletException
    // * @throws IOException
    // */
    //protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //    //get Cart obj
    //    Cart cart = (Cart) req.getSession().getAttribute("cart");
    //    //get user id
    //    User loginUser = (User) req.getSession().getAttribute("user");
    //    //not login
    //    if(loginUser == null){
    //        req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
    //        return;
    //    }
    //    Integer userId = loginUser.getId();
    //
    //    //call orderService.createOrder(Cart, Userid) to create the order
    //    String orderId = orderService.createOrder(cart, userId);
    //    //save the order id in dom
    //    //req.setAttribute("orderId", orderId);
    //    //请求转发到/pages/cart/checkout.jsp
    //    //req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);
    //    req.getSession().setAttribute("orderId", orderId);
    //    resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    //
    //}

    /**
     * create order
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get Cart obj
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //get user id
        User loginUser = (User) req.getSession().getAttribute("user");
        //not login
        if(loginUser == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        Integer userId = loginUser.getId();

        //call orderService.createOrder(Cart, Userid) to create the order


        String orderId = orderService.createOrder(cart, userId);
        //save the order id in dom
        //req.setAttribute("orderId", orderId);
        //请求转发到/pages/cart/checkout.jsp
        //req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);
        req.getSession().setAttribute("orderId", orderId);
        //resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");

        //请求转发到payment page
        resp.sendRedirect(req.getContextPath() + "/pages/cart/payment.jsp");

    }

    /**
     * show all orders for admin manager
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Order> allOrders = orderService.showAllOrders();

        //low efficient process check
        //check for orders if items are all shipped
        for (Order order : allOrders) {
            orderService.checkOrderProcess(order.getOrderId());
        }
        allOrders = orderService.showAllOrders();

        req.setAttribute("allOrders", allOrders);

        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
    }

    /**
     * show all orders normal bidder user
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get user id
        User loginUser = (User) req.getSession().getAttribute("user");
        //not login
        if(loginUser == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        Integer userId = loginUser.getId();

        List<Order> orders = orderService.showMyOrders(userId);

        //low efficient process check
        //check for orders if items are all shipped
        for (Order order : orders) {
            orderService.checkOrderProcess(order.getOrderId());
        }
        orders = orderService.showMyOrders(userId);

        req.setAttribute("orders", orders);

        req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);

    }

    /**
     * show all order items of an order
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showOrderDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get orderId (may need security improvements)
        String orderId = req.getParameter("orderId");

        req.getSession().setAttribute("orderId", orderId);
        //get order items
        List<OrderItem> orderItems = orderService.showOrderDetails(orderId);

        req.setAttribute("orderItems", orderItems);

        req.getRequestDispatcher("/pages/order/orderitems.jsp").forward(req, resp);

    }

    /**
     * show all order items for the seller
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showMySoldItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get user id
        User loginUser = (User) req.getSession().getAttribute("user");
        //not login
        if(loginUser == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        String username = loginUser.getUsername();

        List<OrderItem> soldItems = orderService.showMySoldItems(username);

        req.setAttribute("soldItems", soldItems);

        req.getRequestDispatcher("/pages/order/todelivery.jsp").forward(req, resp);
    }

    /**
     * active for seller to sigh the item as delivering
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void sendOrderItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get item id
        Integer itemId = WebUtils.parseInt(req.getParameter("itemId"), -1);
        //not clear if the int i is necessary just avoid the exception
        int i = orderService.sendOrderItem(itemId);

        resp.sendRedirect(req.getContextPath() + "/orderServlet?action=showMySoldItems");
    }

    /**
     * active for bidder to sigh the item as shipped
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void receiveOrderItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get orderId
        String orderId = (String) req.getSession().getAttribute("orderId");
        //get item id
        Integer itemId = WebUtils.parseInt(req.getParameter("itemId"), -1);
        //not clear if the int i is necessary just avoid the exception
        int i = orderService.receiveOrderItem(itemId);

        resp.sendRedirect(req.getContextPath() + "/orderServlet?action=showOrderDetails&orderId=" + orderId);

    }

//    /**
//     * a check for an order if items are all shipped
//     * @param req
//     * @param resp
//     * @throws ServletException
//     * @throws IOException
//     */
//    protected void checkOrderProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//    }

    /**
     * active for admin manager to delete the shipped order
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get orderId (may need security improvements)
        String orderId = req.getParameter("orderId");

        //delete order
        orderService.deleteOrder(orderId);

        resp.sendRedirect(req.getContextPath() + "/orderServlet?action=showAllOrders");
    }

}
