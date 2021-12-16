package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CreditCardDao;
import com.codecool.shop.model.paymentmodel.CreditCard;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreditCardDaoMem implements CreditCardDao {

    private List<CreditCard> creditCardList = new ArrayList<>();
    private static CreditCardDaoMem instance = null;

    private CreditCardDaoMem() {
    }

    public static CreditCardDaoMem getInstance() {
        if (instance == null) {
            instance = new CreditCardDaoMem();
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
