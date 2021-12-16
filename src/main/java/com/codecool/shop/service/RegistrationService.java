package com.codecool.shop.service;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.UserInfoDaoJdbc;
import com.codecool.shop.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

public class RegistrationService {

    final Logger logger = LoggerFactory.getLogger(RegistrationService.class);
    private final DataSource dataSource;
    private final User user;

    public RegistrationService(DataSource dataSource, User user) {
        this.dataSource = dataSource;
        this.user = user;
    }

    public void addNewUser() {
        UserDao userDao = UserInfoDaoJdbc.getInstance(dataSource);
        userDao.addUser(user);
        logger.info("Registration is successfully! {} username added to database", user.getEmail());
    }
}
