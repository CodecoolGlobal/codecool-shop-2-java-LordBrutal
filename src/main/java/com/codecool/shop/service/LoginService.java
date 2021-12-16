package com.codecool.shop.service;

import com.codecool.shop.dao.implementation.UserInfoJdbc;
import com.codecool.shop.model.UserModel;

import javax.sql.DataSource;

public class LoginService {
    private DataSource dataSource;
    private UserModel userModel;

    public LoginService(DataSource dataSource, UserModel userModel) {
        this.dataSource = dataSource;
        this.userModel = userModel;
    }

    public boolean validetaLogint(){
        UserInfoJdbc userInfoJdbc = UserInfoJdbc.getInstance(dataSource);
        return userInfoJdbc.getUserDetails(userModel.getEmail(), userModel.getPassword());
    }
}
