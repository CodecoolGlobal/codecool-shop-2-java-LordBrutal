package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.PayPalAccountDao;
import com.codecool.shop.model.paymentmodel.PayPalAccount;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PayPalAccountDaoMem implements PayPalAccountDao {

    private List<PayPalAccount> accountList = new ArrayList<>();
    private static PayPalAccountDaoMem instance = null;

    private PayPalAccountDaoMem() {
    }

    public static PayPalAccountDaoMem getInstance() {
        if (instance == null) {
            instance = new PayPalAccountDaoMem();
        }
        return instance;
    }


    @Override
    public void add(PayPalAccount account) {
        accountList.add(account);
    }

    @Override
    public PayPalAccount findAccount(PayPalAccount payPalAccount) {
        return accountList
                .stream()
                .filter(account -> Objects.equals(account.getUsername(), payPalAccount.getUsername()))
                .findFirst()
                .orElse(null);
    }
}
