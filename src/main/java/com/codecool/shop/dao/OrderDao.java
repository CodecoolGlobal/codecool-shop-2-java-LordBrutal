package com.codecool.shop.dao;

import com.codecool.shop.model.CartItem;

import java.util.List;

public interface OrderDao {
    void add(CartItem cartItem);
    boolean isPaymentSuccess();
    void setPaymentSuccess();
    void setTotalPrice(int total);
    void setCartItems(List<CartItem> cartItems);
    void setName(String name);
    String getEmail();
    void setEmail(String email);
    void setPhoneNumber(String phoneNumber);
    void setBillingAddress(String billingAddress);
    void setShippingAddress(String shippingAddress);
    void removeInstance();
    List<CartItem> getCartItems();
}
