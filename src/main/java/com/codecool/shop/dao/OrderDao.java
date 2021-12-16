package com.codecool.shop.dao;

import com.codecool.shop.model.CartItem;

import java.util.List;

public interface OrderDao {
    void add(CartItem cartItem);
    void setPaymentSuccess();
    void setTotalPrice(int total);
    void setCartItems(List<CartItem> cartItems);
    void setName(String name);
    void setEmail(String email);
    void setPhoneNumber(String phoneNumber);
    void setBillingAddress(String billingAddress);
    void setShippingAddress(String shippingAddress);
}
