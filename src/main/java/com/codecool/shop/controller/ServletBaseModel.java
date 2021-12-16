package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.DatabaseManager;

import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class ServletBaseModel extends HttpServlet {

    protected DataSource db;

    {
        try {
            db = new DatabaseManager().connect(getConnectionProperties());
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public ServletBaseModel() {
    }

    protected Properties getConnectionProperties() throws IOException {
        String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
        String appConfigPath = rootPath + "connection.properties";
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(appConfigPath));
        return appProps;
    }
}
