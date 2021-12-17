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

    @Override
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    @Override
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

    @Override
    public void setPaymentSuccess() {
        this.paymentSuccess = true;
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
    public boolean isPaymentSuccess() {
        return paymentSuccess;
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

    @Override
    public boolean hasCart(int userId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id FROM cart WHERE user_id = ?" ;
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("db connection failure");
        }
    }

    @Override
    public void saveCart(int userId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO cart (user_id, cart_items) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, userId);
            String cartJson = new Gson().toJson(cartItems);
            statement.setString(2, cartJson);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
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

    @Override
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

    @Override
    public void emptyCart(int userId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM cart WHERE user_id = ?" ;
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, userId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("db connection failure");
        }
    }

    public void loadBillingInfo(int userId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name, phone_number, shipping_address, billing_address, email " +
                    "FROM billing_info bi" +
                    " JOIN users u on bi.user_id = u.id WHERE user_id = ?";
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

    public void updateBillingInfo(int userId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE billing_info SET name = ?," +
                    "phone_number = ?," +
                    "shipping_address = ?," +
                    "billing_address = ? " +
                    "WHERE user_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, phoneNumber);
            st.setString(3, shippingAddress);
            st.setString(4, billingAddress);
            st.setInt(5, userId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int saveOrder(int userId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO orders (user_id, order_info) VALUES (?, ?)";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, userId);
            String orderJson = new Gson().toJson(this);
            st.setString(2, orderJson);
            return st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadOrder(int orderId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT order_info FROM orders WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, orderId);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                String orderJson = rs.getString(2);
                instance = new Gson().fromJson(orderJson, (Type) OrderDao.class);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
