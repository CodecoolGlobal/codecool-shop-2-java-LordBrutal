package com.codecool.shop.dao;

import com.codecool.shop.model.paymentmodel.PayPalAccount;

public interface PayPalAccountDao {

    void add(PayPalAccount account);
    PayPalAccount findAccount(PayPalAccount payPalAccount);
}
