package com.codecool.shop.service;

import com.codecool.shop.dao.implementation.UserDaoJdbc;
import com.codecool.shop.model.User;

import javax.sql.DataSource;

public class LoginService {
    private DataSource dataSource;
    private User user;

    public LoginService(DataSource dataSource, User user) {
        this.dataSource = dataSource;
        this.user = user;
    }

    public boolean validateLogin(){
        UserDaoJdbc userInfoJdbc = UserDaoJdbc.getInstance(dataSource);
        return userInfoJdbc.getUserDetails(user.getEmail(), user.getHashedPassword());
    }
}
