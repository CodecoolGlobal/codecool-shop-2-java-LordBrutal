package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CreditCardDao;
import com.codecool.shop.model.paymentmodel.CreditCard;
import com.codecool.shop.model.paymentmodel.PayPalAccount;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreditCardDaoJdbc implements CreditCardDao {

    private static CreditCardDaoJdbc instance = null;
    private DataSource dataSource;

    private CreditCardDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static CreditCardDaoJdbc getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new CreditCardDaoJdbc(dataSource);
        }
        return instance;
    }

    @Override
    public void add(CreditCard creditCard) {
    }

    @Override
    public CreditCard findCard(String cardNumber) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT card_holder, exp_year, exp_month, cvv FROM creditcard " +
                    "WHERE card_number = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, cardNumber);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            return new CreditCard(cardNumber,
                    rs.getString(1),
                    Byte.parseByte(rs.getString(2)),
                    Byte.parseByte(rs.getString(3)),
                    Short.parseShort(rs.getString(4)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
