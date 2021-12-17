package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.CartItem;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoMem implements OrderDao {
    private String name;
    private String email;
    private String phoneNumber;
    private String billingAddress;
    private String shippingAddress;
    private boolean paymentSuccess = false;
    private List<CartItem> cartItems = new ArrayList<>();
    private static OrderDaoMem instance = null;
    private int totalPrice;

    private OrderDaoMem() {
    }

    @Override
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    @Override
    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }

    @Override
    public void add(CartItem cartItem) {
        cartItems.add(cartItem);
    }

    @Override
    public void setPaymentSuccess() {
        this.paymentSuccess = true;
    }

    @Override
    public boolean isPaymentSuccess() {
        return paymentSuccess;
    }

    @Override
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public int getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getBillingAddress() {
        return billingAddress;
    }

    @Override
    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    @Override
    public String getShippingAddress() {
        return shippingAddress;
    }

    @Override
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void removeInstance(){
        instance = null;
    }

    //Unused mothods for memory
    @Override
    public boolean hasCart(int userId) {
        return false;
    }
    @Override
    public void saveCart(int userId) {}
    @Override
    public void updateCart(int userId) {}
    @Override
    public void loadCart(int userId) {}
    @Override
    public void emptyCart(int userId) {}
    @Override
    public void loadOrder(int userid) {}
    @Override
    public int saveOrder(int userId) {return -1;}}
