package com.codecool.shop.dao;

import com.codecool.shop.model.User;

public interface UserDao {
    boolean getUserDetails(String email, String password );
    void addUser(User user);
}
