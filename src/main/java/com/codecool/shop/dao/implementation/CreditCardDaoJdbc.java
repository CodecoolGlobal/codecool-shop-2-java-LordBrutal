package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CreditCardDao;
import com.codecool.shop.model.paymentmodel.CreditCard;
import javax.sql.DataSource;

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
        return null;
    }
}
