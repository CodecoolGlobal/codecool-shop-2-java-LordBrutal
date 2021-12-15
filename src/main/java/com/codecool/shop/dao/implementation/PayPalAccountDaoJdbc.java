package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.PayPalAccountDao;
import com.codecool.shop.model.paymentmodel.PayPalAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PayPalAccountDaoJdbc implements PayPalAccountDao {

    private List<PayPalAccount> accountList = new ArrayList<>();
    private static PayPalAccountDaoJdbc instance = null;

    private PayPalAccountDaoJdbc() {
    }

    public static PayPalAccountDaoJdbc getInstance() {
        if (instance == null) {
            instance = new PayPalAccountDaoJdbc();
        }
        return instance;
    }


    @Override
    public void add(PayPalAccount account) {
        accountList.add(account);
    }

    @Override
    public PayPalAccount findAccount(String userName) {
        return accountList
                .stream()
                .filter(account -> Objects.equals(account.getUsername(), userName))
                .findFirst()
                .orElse(null);
    }
}
