package com.atguigu.test;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.mysql.jdbc.log.NullLogger;
import org.junit.Test;

import java.math.BigDecimal;
import java.nio.channels.DatagramChannel;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {

    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null, "justTry123", "dch", new BigDecimal(999), 10000, 0, null, null, "2020/8/27 16:08:00"));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(42);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21, "大家要努力呀", "dch", new BigDecimal(999), 10000, 0, null, "dch", "2020/8/27 16:08:00"));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(41));
    }

    @Test
    public void queryBookByBidder() {
        for (Book book : bookDao.queryBookByBidder("admin")) {
            System.out.println(book);
        }
    }

    @Test
    public void queryBookByOwner() {
        for (Book book : bookDao.queryBookByOwner("dch01")) {
            System.out.println(book);
        }
    }

    @Test
    public void queryBooks() {
        for (Book book : bookDao.queryBooks()) {
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10, 50));
    }

    @Test
    public void queryForPageItems() {
        for (Book book : bookDao.queryForPageItems(8, Page.PAGE_SIZE)) {
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageItemsByPrice() {
        for (Book book : bookDao.queryForPageItemsByPrice(0, Page.PAGE_SIZE, 10, 50)) {
            System.out.println(book);
        }
    }
}