package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.CartItem;
import com.google.gson.Gson;
import javax.sql.DataSource;
import java.lang.reflect.Type;
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

    public void loadBillingInfo(int userId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name, phone_number, shipping_address, billing_address " +
                    "FROM billing_info " +
                    "WHERE user_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, userId);
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

    public boolean hasCart(int userId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id FROM cart WHERE user_id = ?" ;
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            return !rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("db connection failure");
        }
    }

    public void saveCart(int userId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO cart (user_id, cart_items) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, userId);
            String cartJson = new Gson().toJson(cartItems);
            statement.setString(2, cartJson);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCart(int userId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE cart SET cart_items = ? WHERE user_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(2, userId);
            String cartJson = new Gson().toJson(cartItems);
            statement.setString(1, cartJson);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadCart(int userId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT cart_items FROM cart WHERE user_id = ?" ;
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                String cartJson = rs.getString(1);
                cartItems = new Gson().fromJson(cartJson, (Type) CartItem[].class);
            }
        } catch (SQLException e) {
            throw new RuntimeException("db connection failure");
        }
    }

    public void emptyCart(int userId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM cart WHERE user_id = ?" ;
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("db connection failure");
        }
    }
}
