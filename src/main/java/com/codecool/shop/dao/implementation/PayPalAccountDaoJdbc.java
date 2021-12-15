package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.paymentmodel.PayPalAccount;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PayPalAccountDaoJdbc {

    private static PayPalAccountDaoJdbc instance = null;
    private DataSource dataSource;

    private PayPalAccountDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static PayPalAccountDaoJdbc getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new PayPalAccountDaoJdbc(dataSource);
        }
        return instance;
    }

    public PayPalAccount findAccount(int userId, String userName) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, user_id, username, password FROM paypal pp " +
                    "WHERE pp.user_id = ? AND pp.username = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, userId);
            st.setString(2, userName);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            return new PayPalAccount(rs.getString(3), rs.getString(4));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
