package com.codecool.shop.dao;

import com.codecool.shop.model.User;

import java.util.List;

public interface UserDao {
    boolean getUserDetails(String email, String password );
    void addUser(User user);
    List<String> getAllUser();
}
