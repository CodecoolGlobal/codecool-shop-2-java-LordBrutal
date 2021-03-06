package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import javax.sql.DataSource;
import java.sql.*;

public class UserDaoJdbc implements UserDao {
    private static UserDaoJdbc instance = null;
    private DataSource dataSource;

    private UserDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static UserDaoJdbc getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new UserDaoJdbc(dataSource);
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
            throw new RuntimeException("db connection is failed");
        }
    }

    @Override
    public void addUser(User user) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO users (email, password) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getHashedPassword());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("db connection is failed");
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
