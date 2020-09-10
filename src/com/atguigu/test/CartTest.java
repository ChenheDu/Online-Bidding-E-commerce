package com.atguigu.test;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1, "Web Design", 1, new BigDecimal(999), new BigDecimal(999), "abc"));
        cart.addItem(new CartItem(1, "Web Design", 1, new BigDecimal(999), new BigDecimal(999), "abc"));
        cart.addItem(new CartItem(2, "Android App", 1, new BigDecimal(88), new BigDecimal(88), "abc"));

        System.out.println(cart);

    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1, "Web Design", 1, new BigDecimal(999), new BigDecimal(999), "abc"));
        cart.addItem(new CartItem(1, "Web Design", 1, new BigDecimal(999), new BigDecimal(999), "abc"));
        cart.addItem(new CartItem(2, "Android App", 1, new BigDecimal(88), new BigDecimal(88), "abc"));

        cart.deleteItem(1);

        System.out.println(cart);

    }

    @Test
    public void clear() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1, "Web Design", 1, new BigDecimal(999), new BigDecimal(999), "abc"));
        cart.addItem(new CartItem(1, "Web Design", 1, new BigDecimal(999), new BigDecimal(999), "abc"));
        cart.addItem(new CartItem(2, "Android App", 1, new BigDecimal(88), new BigDecimal(88), "abc"));

        cart.clear();

        System.out.println(cart);

    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1, "Web Design", 1, new BigDecimal(999), new BigDecimal(999), "abc"));
        cart.addItem(new CartItem(1, "Web Design", 1, new BigDecimal(999), new BigDecimal(999), "abc"));
        cart.addItem(new CartItem(2, "Android App", 1, new BigDecimal(88), new BigDecimal(88), "abc"));

        cart.clear();

        cart.addItem(new CartItem(1, "Web Design", 1, new BigDecimal(999), new BigDecimal(999), "abc"));
        cart.updateCount(1, 5);

        System.out.println(cart);

    }
}