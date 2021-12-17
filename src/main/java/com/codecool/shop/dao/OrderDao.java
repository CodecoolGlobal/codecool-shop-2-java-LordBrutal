package com.codecool.shop.dao;

import com.codecool.shop.model.CartItem;

import java.util.List;

public interface OrderDao {
    void add(CartItem cartItem);
    boolean isPaymentSuccess();
    void setPaymentSuccess();
    int getTotalPrice();
    void setTotalPrice(int total);
    List<CartItem> getCartItems();
    void setCartItems(List<CartItem> cartItems);
    String getName();
    void setName(String name);
    String getEmail();
    void setEmail(String email);
    String getPhoneNumber();
    void setPhoneNumber(String phoneNumber);
    String getBillingAddress();
    void setBillingAddress(String billingAddress);
    String getShippingAddress();
    void setShippingAddress(String shippingAddress);
    void removeInstance();
}
