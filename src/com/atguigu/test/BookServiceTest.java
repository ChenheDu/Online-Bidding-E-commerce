package com.atguigu.test;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class BookServiceTest {

    private BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null, "justTry123", "dch", new BigDecimal(99.9), 998, 20, null, null, "2020/8/27 16:08:0"));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(43);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(20, "cDayyyy", "dchnbb", new BigDecimal(999.9), 1001, 15, null,null, "2020/8/27 16:08:0"));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(43));
    }

    @Test
    public void queryBookByBidder() {
        for (Book queryBookByBidder : bookService.queryBookByBidder("admin")) {
            System.out.println(queryBookByBidder);
        }
    }

    @Test
    public void queryBookByOwner() {
        for (Book queryBookByOwner : bookService.queryBookByOwner("dch01")) {
            System.out.println(queryBookByOwner);
        }
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookService.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void page(){
        Page<Book> page = bookService.page(1, Page.PAGE_SIZE);
        System.out.println(page);
    }

    @Test
    public void pageByPrice(){
        Page<Book> page = bookService.pageByPrice(1, Page.PAGE_SIZE, 10, 50);
        System.out.println(page);
    }
}