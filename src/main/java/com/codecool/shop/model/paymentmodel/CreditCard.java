package com.codecool.shop.model.paymentmodel;

public class CreditCard {

    private String cardNumber;
    private String cardHolder;
    private byte expYear;
    private byte expMonth;
    private short cvv;

    public CreditCard(String cardNumber, String cardHolder, byte expYear, byte expMonth, short cvv) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expYear = expYear;
        this.expMonth = expMonth;
        this.cvv = cvv;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public byte getExpYear() {
        return expYear;
    }

    public byte getExpMonth() {
        return expMonth;
    }

    public short getCvv() {
        return cvv;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", cardHolder='" + cardHolder + '\'' +
                ", expYear=" + expYear +
                ", expMonth=" + expMonth +
                ", cvv=" + cvv +
                '}';
    }
}
