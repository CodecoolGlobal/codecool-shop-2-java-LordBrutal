package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.PayPalAccountDao;
import com.codecool.shop.model.paymentmodel.PayPalAccount;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PayPalAccountDaoMem implements PayPalAccountDao {

    private List<PayPalAccount> accountList = new ArrayList<>();
    private static PayPalAccountDaoMem instance = null;
    private DataSource dataSource;

    private PayPalAccountDaoMem(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static PayPalAccountDaoMem getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new PayPalAccountDaoMem(dataSource);
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
