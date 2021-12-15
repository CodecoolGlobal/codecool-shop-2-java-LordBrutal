package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CreditCardDao;
import com.codecool.shop.model.paymentmodel.CreditCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreditCardDaoJdbc implements CreditCardDao {

    private List<CreditCard> creditCardList = new ArrayList<>();
    private static CreditCardDaoJdbc instance = null;

    private CreditCardDaoJdbc() {
    }

    public static CreditCardDaoJdbc getInstance() {
        if (instance == null) {
            instance = new CreditCardDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(CreditCard creditCard) {
        creditCardList.add(creditCard);
    }

    @Override
    public CreditCard findCard(String cardNumber) {
        return creditCardList
                .stream()
                .filter(creditCard1 -> Objects.equals(creditCard1.getCardNumber(), cardNumber))
                .findFirst()
                .orElse(null);
    }
}
