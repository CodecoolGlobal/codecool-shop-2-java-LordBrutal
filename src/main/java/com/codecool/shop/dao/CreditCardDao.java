package com.codecool.shop.dao;

import com.codecool.shop.model.paymentmodel.CreditCard;

public interface CreditCardDao {

    void add(CreditCard creditCard);
    CreditCard findCard(String cardNumber);

}
