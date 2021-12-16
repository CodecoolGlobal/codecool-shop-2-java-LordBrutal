package com.codecool.shop.service;

import com.codecool.shop.dao.implementation.UserInfoDaoJdbc;
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
        UserInfoDaoJdbc userInfoJdbc = UserInfoDaoJdbc.getInstance(dataSource);
        return userInfoJdbc.getUserDetails(userModel.getEmail(), userModel.getPassword());
    }
}
