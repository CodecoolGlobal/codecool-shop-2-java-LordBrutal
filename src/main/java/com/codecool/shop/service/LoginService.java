package com.codecool.shop.service;

import com.codecool.shop.dao.implementation.UserInfoDaoJdbc;
import com.codecool.shop.model.User;

import javax.sql.DataSource;

public class LoginService {
    private DataSource dataSource;
    private User user;

    public LoginService(DataSource dataSource, User user) {
        this.dataSource = dataSource;
        this.user = user;
    }

    public boolean validetaLogint(){
        UserInfoDaoJdbc userInfoJdbc = UserInfoDaoJdbc.getInstance(dataSource);
        return userInfoJdbc.getUserDetails(user.getEmail(), user.getHashedPassword());
    }
}
