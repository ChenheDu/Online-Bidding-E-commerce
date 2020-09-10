package com.atguigu.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Book {

    private Integer id;
    private String name;
    private String owner;
    private BigDecimal price;
    private Integer sales;
    private Integer stock;


    //private String imgPath = "static/img/default.jpg";
    private String imgPath = "https://images.indianexpress.com/2020/08/MacBook-Air-2020-main.jpg";
    private String bidder;
    private String date;

    public Book() {
    }

    public Book(Integer id, String name, String owner, BigDecimal price, Integer sales, Integer stock, String imgPath, String bidder, String date) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
        this.imgPath = imgPath;
        this.bidder = bidder;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getBidder() {
        return bidder;
    }

    public void setBidder(String bidder) {
        this.bidder = bidder;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }


    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        //要求给定的图书封面图片路径不能为空
        if(imgPath != null && !"".equals(imgPath)){
            this.imgPath = imgPath;
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", price=" + price +
                ", sales=" + sales +
                ", stock=" + stock +
                ", bidder='" + bidder + '\'' +
                ", date=" + date + '\'' +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
