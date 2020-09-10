package com.atguigu.dao.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {

    @Override
    public int addBook(Book book) {

        String sql = "INSERT INTO t_book(`name`,`owner`,`price`,`sales`,`stock`,`img_path`,`bidder`,`date`) \n" +
                "VALUES(?,?,?,?,?,?,?,?);";

        return update(sql,book.getName(), book.getOwner(), book.getPrice(), book.getSales(),
                book.getStock(), book.getImgPath(), book.getBidder(), book.getDate());
    }

    @Override
    public int deleteBookById(Integer id) {

        String sql = "delete from t_book where id = ?";
        return update(sql, id);
    }

    @Override
    public int updateBook(Book book) {

        String sql = "update t_book set `name`=?,`owner`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=?,`bidder`=?,`date`=? where id = ?";
        return update(sql, book.getName(),book.getOwner(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getBidder(),book.getDate(), book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {

        String sql = "select `id`,`name`,`owner`,`price`,`sales`,`stock`,`img_path` imgPath, `bidder`,`date` from t_book where id = ?";
        return queryForOne(Book.class, sql, id);
    }

    @Override
    public List<Book> queryBookByBidder(String bidder) {

        String sql = "select `id`,`name`,`owner`,`price`,`sales`,`stock`,`img_path` imgPath, `bidder`,`date` from t_book where bidder = ?";
        return queryForList(Book.class, sql, bidder);
    }

    @Override
    public List<Book> queryBookByOwner(String owner) {
        String sql = "select `id`,`name`,`owner`,`price`,`sales`,`stock`,`img_path` imgPath, `bidder`,`date` from t_book where owner = ?";
        return queryForList(Book.class, sql, owner);
    }

    @Override
    public List<Book> queryBooks() {

        String sql = "select `id`,`name`,`owner`,`price`,`sales`,`stock`,`img_path` imgPath, `bidder`,`date` from t_book";
        return queryForList(Book.class, sql);
    }

    @Override
    public Integer queryForPageTotalCount() {

        String sql = "select count(*) from t_book";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {

        String sql = "select count(*) from t_book where price between ? and ?";
        Number count = (Number) queryForSingleValue(sql, min, max);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {

        String sql = "select `id`,`name`,`owner`,`price`,`sales`,`stock`,`img_path` imgPath, `bidder`,`date` from t_book limit ?,?";
        return queryForList(Book.class, sql, begin, pageSize);
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {

        String sql = "select `id`,`name`,`owner`,`price`,`sales`,`stock`,`img_path` imgPath, `bidder`,`date` " +
                "from t_book where price between ? and ? order by price limit ?,?";
        return queryForList(Book.class, sql, min, max, begin, pageSize);
    }
}
