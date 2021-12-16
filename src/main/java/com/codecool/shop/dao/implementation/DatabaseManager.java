package com.codecool.shop.dao.implementation;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

import org.postgresql.ds.PGSimpleDataSource;

public class DatabaseManager {

    public DatabaseManager() throws SQLException {
    }

    public DataSource connect(Properties properties) throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = properties.getProperty("database");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        int portnumber = Integer.parseInt(properties.getProperty("portnumber"));
        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setPortNumber(portnumber);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}
