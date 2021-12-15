package com.codecool.shop.dao;

import com.codecool.shop.model.CartItem;

public interface OrderDao {
    void add(CartItem cartItem);
    void setPaymentSuccess();
}
