package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.CartItem;


import java.util.ArrayList;
import java.util.List;

public class OrderDaoMem {
    private boolean paymentSuccess = false;
    private List<CartItem> cartItems = new ArrayList<CartItem>();
    private static OrderDaoMem instance = null;
    private int totalPrice;

    private OrderDaoMem() {
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }

    public void add(CartItem cartItem) {
        cartItems.add(cartItem);
    }

    public void setPaymentSuccess() {
        this.paymentSuccess = true;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
