package com.codecool.shop.service;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.UserInfoDaoJdbc;
import com.codecool.shop.model.User;

import javax.sql.DataSource;

public class RegistrationService {

    private final DataSource dataSource;
    private final User user;

    public RegistrationService(DataSource dataSource, User user) {
        this.dataSource = dataSource;
        this.user = user;
    }

    public void addNewUser() {
        UserDao userDao = UserInfoDaoJdbc.getInstance(dataSource);
        userDao.addUser(user);
    }
}
