package com.atguigu.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * cart object
 */
public class Cart {

    private Integer totalCount;
    private BigDecimal totalPrice;
    //使用局部变量代替global 可以make memory less
    /**
     * key is goods number
     * value is goods info
     */
    private Map<Integer, CartItem> items = new LinkedHashMap<Integer,CartItem>();

    /**
     * add shopping item
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
        //check if there is same item in cart
        CartItem item = items.get(cartItem.getId());
        if(item == null){
            //no such items added before
            items.put(cartItem.getId(), cartItem);
        }else{
            //added before
            item.setCount(item.getCount() + 1); //count + 1
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount()))); // refresh total price
        }
    }

    /**
     * delete shopping item
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * clear shopping cart
     */
    public void clear(){
        items.clear();
    }

    /**
     * modify shopping items number
     */
    public void updateCount(Integer id, Integer count){
        //1. check if the item is in cart
        CartItem cartItem = items.get(id);
        if(cartItem != null){
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }


    public Integer getTotalCount() {

        totalCount = 0;
        for(Map.Entry<Integer, CartItem>entry : items.entrySet()){
            totalCount += entry.getValue().getCount();
        }

        //for (CartItem value : items.values()) {
        //
        //} method 2

        return totalCount;
    }

    //public void setTotalCount(Integer totalCount) {
    //    this.totalCount = totalCount;
    //}

    public BigDecimal getTotalPrice() {

        totalPrice = new BigDecimal(0);

        for(Map.Entry<Integer, CartItem>entry : items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }

        return totalPrice;
    }

    //public void setTotalPrice(BigDecimal totalPrice) {
    //    this.totalPrice = totalPrice;
    //}

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
