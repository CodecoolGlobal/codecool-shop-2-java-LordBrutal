package com.codecool.shop.model.paymentmodel;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return expYear == that.expYear && expMonth == that.expMonth && cvv == that.cvv && Objects.equals(cardNumber, that.cardNumber) && Objects.equals(cardHolder, that.cardHolder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, cardHolder, expYear, expMonth, cvv);
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
