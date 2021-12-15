package com.codecool.shop.service;

import com.codecool.shop.dao.CreditCardDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.PayPalAccountDao;
import com.codecool.shop.model.paymentmodel.CreditCard;
import com.codecool.shop.model.paymentmodel.PayPalAccount;

public class PaymentValidationService {
    private final CreditCardDao creditCardDataStore;
    private final PayPalAccountDao payPalAccountDataStore;
    private final OrderDao orderDao;

    public PaymentValidationService(CreditCardDao creditCardDataStore, PayPalAccountDao payPalAccountDataStore, OrderDao orderDao) {
        this.creditCardDataStore = creditCardDataStore;
        this.payPalAccountDataStore = payPalAccountDataStore;
        this.orderDao = orderDao;
    }

    public boolean validateCreditCard(CreditCard creditCard) {
        CreditCard validCreditCard = creditCardDataStore.findCard(creditCard.getCardNumber());
        if (validCreditCard != null) {
            if (creditCard.equals(validCreditCard)) {
                orderDao.setPaymentSuccess();
                return true;
            }
        }
        return false;
    }

    public boolean validatePayPalAccount(PayPalAccount payPalAccount) {
        PayPalAccount validAccount = payPalAccountDataStore.findAccount(payPalAccount.getUsername());
        if (validAccount != null) {
            if (payPalAccount.equals(validAccount)) {
                orderDao.setPaymentSuccess();
                return true;
            }
        }
        return false;
    }
}
