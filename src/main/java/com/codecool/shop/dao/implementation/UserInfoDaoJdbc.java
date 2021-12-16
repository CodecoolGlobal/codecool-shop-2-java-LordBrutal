package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.UserDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfoDaoJdbc implements UserDao {
    private static UserInfoDaoJdbc instance = null;
    private DataSource dataSource;

    private UserInfoDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static UserInfoDaoJdbc getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new UserInfoDaoJdbc(dataSource);
        }
        return instance;
    }

    @Override
    public boolean getUserDetails(String email, String password) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM users WHERE email = ? and password = ?" ;
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("bd connection is failed");
        }
    }

    public int getUserId(String email) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id FROM users WHERE email = ?" ;
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                throw new RuntimeException();
            }
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException("db connection failure");
        }
    }
}
