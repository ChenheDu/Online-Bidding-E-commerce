package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.pojo.User;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class BookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * 处理分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用BookService.page(pageNo, pageSize) : page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        //设置后台url地址
        page.setUrl("manager/bookServlet?action=page");

        //3.保存page对象到Request域中
        req.setAttribute("page", page);
        //4.请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo += 1;

        ///**
        // * check if the photo is a upload file, if it is, upload and revalue the img_path-------------
        // */
        //
        //System.out.println(ServletFileUpload.isMultipartContent(req));
        ////1. check if upload data is mutiple
        //if(ServletFileUpload.isMultipartContent(req)){
        //    //create fileitemfactory imp class
        //    FileItemFactory fileItemFactory = new DiskFileItemFactory();
        //    //create parsing utils class for upload data
        //    ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
        //    //parse uploaded data get all fileitems
        //    try {
        //        List<FileItem> list = servletFileUpload.parseRequest(req);
        //        //循环判断每一个表单项是普通类型还是上传图片
        //        for (FileItem fileItem : list) {
        //
        //            if(!fileItem.isFormField()){
        //                //上传data非普通，为图片
        //                fileItem.setFieldName(req.getParameter("username") + req.getParameter("name") + new Date());
        //                fileItem.write(new File(req.getContextPath() + "/img_item"));
        //                System.out.println("filename: " + fileItem.getName());
        //            }
        //        }
        //
        //    } catch (Exception e) {
        //        e.printStackTrace();
        //    }
        //
        //}
        //
        //
        ///**
        // * -------------------------------------------------------------------------------------------
        // */


        //1.获取请求的参数==封装成为Book对象
        Book book = WebUtils.copuParamToBean(req.getParameterMap(), new Book());
        //2.调用BookService.addBook()保存图书
        bookService.addBook(book);

        //upload data:
        //1 check if the


        //3.跳到图书列表页面  /manager/bookServlet?action=list
        //req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req, resp);
        //browser会记录最后一次操作，F5刷新会导至重复进行最后一次操作，例如继续添加同一个信息，这里不能用转发，要用重定向
        //重定向是两次请求的第一个 / 表示端口号


        User user = (User) req.getSession().getAttribute("user");
        String username = user.getUsername();
        if(!"admin".equals(username)){
            resp.sendRedirect( req.getContextPath() + "/manager/bookServlet?action=listUploadBook");
            return;
        }

        resp.sendRedirect( req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
        //System.out.println(req.getContextPath());

    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数id，图书编辑
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //2.调用bookService.deleteBookById();删除图书
        bookService.deleteBookById(id);

        User user = (User) req.getSession().getAttribute("user");
        String username = user.getUsername();
        if(!"admin".equals(username)){
            resp.sendRedirect( req.getContextPath() + "/manager/bookServlet?action=listUploadBook");
            return;
        }

        //3.重定向回图书列表管理页面
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String username = req.getParameter("username");
//        if(username == null || "".equals(username)){
//            req.getRequestDispatcher("index.jsp").forward(req,resp);
//            return;
//        }
        //1.获取请求的参数==封装成为Book对象
        Book book = WebUtils.copuParamToBean(req.getParameterMap(), new Book());
        //2.调用BookService.updateBook(book); 修改图书
        bookService.updateBook(book);
        //3.重定向回图书列表管理页面 地址 /工程名/manager/bookServlet?action=list
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数图书编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //2.调用bookService.queryBookById查询图书
        Book book = bookService.queryBookById((id));
        //3.保存图书到Request域中
        req.setAttribute("book", book);
        //4.请求转发到 pages/manager/book_edit.jsp
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    protected void bidBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数图书编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //2.调用bookService.queryBookById查询图书
        Book book = bookService.queryBookById((id));
        //3.保存图书到Request域中
        req.setAttribute("book", book);
        //4.请求转发到 pages/manager/book_edit.jsp
        req.getRequestDispatcher("/pages/user/bidding.jsp").forward(req, resp);
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.通过BookService查询全部图书
        List<Book> books = bookService.queryBooks();
        //2.把全部图书保存到Request域中
        req.setAttribute("books", books);

        //3.请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);

    }

    protected void updateBiddingBooks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.获取请求的参数==封装成为Book对象
        Book book = WebUtils.copuParamToBean(req.getParameterMap(), new Book());
        //2.调用BookService.updateBook(book); 修改图书
        if(book.getId()!= null && book != null){
            bookService.updateBook(book);
        }

        //3.get the bidder info
        String bidder = req.getParameter("bidder");
        if(bidder == null){
            User user = (User) req.getSession().getAttribute("user");
            bidder = user.getUsername();
        }

        List<Book> biddingbooks = bookService.queryBookByBidder(bidder);
        //4.把全部图书保存到Request域中
        req.setAttribute("books", biddingbooks);

        //5.请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/cart/bidding_process.jsp").forward(req, resp);

    }

    protected void listUploadBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.get the owner info
        String owner = req.getParameter("owner");
        if(owner == null){
            User user = (User) req.getSession().getAttribute("user");
            owner = user.getUsername();
        }

        List<Book> listUploadBooks = bookService.queryBookByOwner(owner);
        if(listUploadBooks.size() == 0 || listUploadBooks == null){
            //2.如果list为空，跳转回upload page
            req.getRequestDispatcher("/pages/user/upload.jsp").forward(req, resp);
        } else {
            //3.把全部图书保存到Request域中
            req.setAttribute("listuploadBooks", listUploadBooks);

            //4.请求转发到/pages/manager/book_manager.jsp页面
            req.getRequestDispatcher("/pages/user/upload_display.jsp").forward(req, resp);
        }



    }
}
