package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CreditCardDao;
import com.codecool.shop.model.paymentmodel.CreditCard;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreditCardDaoJdbc implements CreditCardDao {

    private List<CreditCard> creditCardList = new ArrayList<>();
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
            String sql = "SELECT id, card_number, card_holder, exp_year, exp_month, cvv FROM creditcard cc " +
                    "WHERE cc.card_number = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, cardNumber);
            ResultSet rs = conn.createStatement().executeQuery(sql);
            if (!rs.next()) {
                return null;
            }
            return new CreditCard(rs.getString(2), rs.getString(3),
                    Byte.parseByte(rs.getString(4)),
                    Byte.parseByte(rs.getString(5)),
                    Short.parseShort(rs.getString(6)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
