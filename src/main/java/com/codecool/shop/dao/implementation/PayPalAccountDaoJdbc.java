package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.PayPalAccountDao;
import com.codecool.shop.model.paymentmodel.PayPalAccount;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PayPalAccountDaoJdbc implements PayPalAccountDao {

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
    @Override
    public PayPalAccount findAccount(PayPalAccount payPalAccount) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, user_id, username, password FROM paypal pp " +
                    "WHERE pp.username = ? AND pp.password = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, payPalAccount.getUsername());
            st.setString(2, payPalAccount.getPassword());
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            return new PayPalAccount(rs.getString(3), rs.getString(4));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(PayPalAccount account) {

    }

}
