package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.DatabaseManager;

import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;
import java.sql.SQLException;

public class ServletBaseModel extends HttpServlet {
    protected DataSource db;

    {
        try {
            db = new DatabaseManager().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
