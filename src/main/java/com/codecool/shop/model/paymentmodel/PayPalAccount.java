package com.codecool.shop.model.paymentmodel;

public class PayPalAccount {

    private String username;
    private String password;

    public PayPalAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
