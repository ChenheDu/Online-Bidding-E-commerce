package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.User;
import com.atguigu.service.BookService;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();
    private BookService bookService = new BookServiceImpl();

    /**
     * 处理登录的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.delete user info in Session/or delete Session
        req.getSession().invalidate();

        //2.re nevigation to index.jsp
        resp.sendRedirect(req.getContextPath());
    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User loginUser = userService.login(new User(null, username, password, null));
        //2.检查用户名和密码是否正确
        if(loginUser == null){
            //错误
            //把错误信息，和回显的表单项信息，保存到request域中
            req.setAttribute("msg", "User name or password error");
            req.setAttribute("username", username);
            //跳转回登录页面
            req.getRequestDispatcher("pages/user/login.jsp").forward(req, resp);
        }else {
            //正确
            //登录
            //store the user info to session domain
            req.getSession().setAttribute("user", loginUser);
            //返回登陆成功页面
            req.getRequestDispatcher("pages/user/login_success.jsp").forward(req, resp);
        }
    }

    /**
     * 处理注册的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get verification from Session
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //delete verification from Session
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //1.获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        //User user = new User();

        //测试代码
        //Map<String, String[]> parameterMap = req.getParameterMap();
        //for(Map.Entry<String, String[]> entry : parameterMap.entrySet()){
        //    System.out.println(entry.getKey() + " = " + Arrays.asList(entry.getValue()));
        //}

        User user = WebUtils.copuParamToBean(req.getParameterMap(), new User());

        //2.检查验证码是否正确 (先cast 为 abcde）
        if(token != null && token.equalsIgnoreCase(code)){
            //正确
            //3.检查用户名和密码是否可用
            if(userService.existsUsername(username)){
                //不可用
                System.out.println("User[" + username + "]exists");
                //跳回注册页面
                System.out.println("User exists");

                //把回显信息，保存到request域中
                req.setAttribute("msg", "User name exists");
                req.setAttribute("username", username);
                req.setAttribute("email", email);

                req.getRequestDispatcher("pages/user/regist.jsp").forward(req, resp);
            }else{
                //可用
                //调用service 保存到数据库
                userService.registUser(new User(null, username, password, email));
                //跳到注册成功页面
                req.getRequestDispatcher("pages/user/regist_success.jsp").forward(req, resp);
            }
        }else {
            //把回显信息，保存到request域中
            req.setAttribute("msg", "Verification code error");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            //不正确
            //跳回注册页面
            System.out.println("Verification code [" + code + "] error");
            req.getRequestDispatcher("pages/user/regist.jsp").forward(req, resp);
        }
    }

    /**
     * 添加的一个简易无验证的信用卡支付记录功能(8-10-2020)
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void pay(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get user id
        User loginUser = (User) req.getSession().getAttribute("user");
        //not login
        if(loginUser == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }

        //1.获取请求的参数
        String cardnumber = req.getParameter("cardnumber");
        String holdername = req.getParameter("holdername");
        String paymentcode = req.getParameter("paymentcode");
        String cardvalidity = req.getParameter("cardvalidity");

        //User user = new User();

        //测试代码
        //Map<String, String[]> parameterMap = req.getParameterMap();
        //for(Map.Entry<String, String[]> entry : parameterMap.entrySet()){
        //    System.out.println(entry.getKey() + " = " + Arrays.asList(entry.getValue()));
        //}

        //User user = WebUtils.copuParamToBean(req.getParameterMap(), new User());
        //
        //2.检查验证码是否正确 (先cast 为 abcde）
        if(cardnumber == null ||"".equals(cardnumber) ){
            req.setAttribute("cardnumber", "Card number cannot be empty");

            req.getRequestDispatcher("pages/cart/payment.jsp").forward(req, resp);
        }else{
            //get Cart obj
            Cart cart = (Cart) req.getSession().getAttribute("cart");
            //清空购物车
            cart.clear();

            //get the checkout books info from session
            List<Book> biddedbooks = (List<Book>) req.getSession().getAttribute("biddedbooks");
            Iterator<Book> iterator = biddedbooks.iterator();
            while (iterator.hasNext()){
                Book book = iterator.next();
                bookService.deleteBookById(book.getId());
            }

            req.getSession().setAttribute("cardnumber", cardnumber);
            req.getRequestDispatcher("pages/cart/checkout.jsp").forward(req, resp);
        }

    }


}
