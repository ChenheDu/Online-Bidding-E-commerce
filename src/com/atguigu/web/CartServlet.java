package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.pojo.User;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.zip.CheckedOutputStream;

public class CartServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();

    /**
     * modify the count of item in cart
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get the item id/count from the request
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);

        //get the cart obj
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if(cart != null){
            //modify the number of items
            cart.updateCount(id, count);
            //redirector to previous page
            resp.sendRedirect(req.getHeader("referer"));
        }
    }

    /**
     * clear all items from cart
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get Cart obj
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null){
            //clear the cart
            cart.clear();
            //redirector to the previous page
            resp.sendRedirect(req.getHeader("referer"));
        }
    }

    /**
     * delete item from cart
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get the id of items
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //get Cart obj
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if(cart != null){
            //delete the item
            cart.deleteItem(id);
            //redirector to the previous page
            resp.sendRedirect(req.getHeader("referer"));
        }
    }


    /**
     * click to add to cart
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //System.out.println("add to cart");  (test)
        //System.out.println("id of goods " + req.getParameter("id"));

        //get the id of goods
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //call bookService.queryBookById() get the info of Book
        Book book = bookService.queryBookById(id);
        //transfer book info into CartItem
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice(), book.getOwner());
        //call Cart.addItem(CartItem) to add goods
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);

        System.out.println(cart);
        System.out.println("request header referer value is :" + req.getHeader("referer"));

        //name of last added item
        req.getSession().setAttribute("lastName", cartItem.getName());

        //redirector to page which u click the button
        resp.sendRedirect(req.getHeader("referer"));

    }

    /**
     * click cart will search the db to define items reached the deadline and switch them into bidder's cart
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.get the bidder info
        String bidder = req.getParameter("bidder");
        if(bidder == null){
            User user = (User) req.getSession().getAttribute("user");
            bidder = user.getUsername();
        }

        List<Book> biddedbooks = bookService.queryBookByBidder(bidder);
        Date currentTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //删除迭代器可以避免在循环调用中删除ele导致 ConcurrentModificationException
        Iterator<Book> iterator = biddedbooks.iterator();

        //get the cart or create a new one
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        //避免重复添加，待优化
        cart.clear();

        //for ( book : biddedbooks){
        while (iterator.hasNext()){
            Date deadline = null;
            Book book = iterator.next();
            try {
                //String date = book.getDate().substring(0,19);
                String date = book.getDate();
                //System.out.println(date);
                deadline = sdf.parse(date);
                //deadline = sdf.parse(sdf.format(new Date(date)));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(currentTime.before(deadline)){
                //biddedbooks.remove(book);
                iterator.remove();
            }else{
                //transfer book info into CartItem
                CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice(), book.getOwner());

                //call Cart.addItem(CartItem) to add goods
                cart.addItem(cartItem);
                //name of last added item
                req.getSession().setAttribute("lastName", cartItem.getName());
            }

            if(biddedbooks.isEmpty()){
                req.getRequestDispatcher("pages/cart/cart.jsp").forward(req, resp);
                return;
            }
        }

        req.getSession().setAttribute("biddedbooks", biddedbooks);
        resp.sendRedirect("pages/cart/cart.jsp");
    }
}
