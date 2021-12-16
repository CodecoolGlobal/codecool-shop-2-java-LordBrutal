package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.CartItem;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoJdbc implements OrderDao {
    private String name;
    private String email;
    private String phoneNumber;
    private String billingAddress;
    private String shippingAddress;
    private boolean paymentSuccess = false;
    private List<CartItem> cartItems = new ArrayList<>();
    private static OrderDaoJdbc instance = null;
    private int totalPrice;
    private DataSource dataSource;

    private OrderDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public static OrderDaoJdbc getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new OrderDaoJdbc(dataSource);
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

    public int getTotalPrice() {
        return totalPrice;
    }

    public boolean isPaymentSuccess() {
        return paymentSuccess;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void removeInstance(){
        instance = null;
    }

    public void loadBillingInfo(String email) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name, phone_number, shipping_address, billing_address, email " +
                    "FROM billing_info bi " +
                    "JOIN users u on bi.user_id = u.id WHERE u.email = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return;
            }
            setName(rs.getString(1));
            setPhoneNumber(rs.getString(2));
            setShippingAddress(rs.getString(3));
            setBillingAddress(rs.getString(4));
            setEmail(rs.getString(5));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
