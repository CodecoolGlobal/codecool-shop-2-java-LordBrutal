package com.codecool.shop.dao;

import com.codecool.shop.model.UserModel;

public interface UserDao {
    boolean getUserDetails(String email, String password );
}
