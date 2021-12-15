package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao {

    private static SupplierDaoJdbc instance = null;
    private DataSource dataSource;

    /* A private Constructor prevents any other class from instantiating.
     */
    private SupplierDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static SupplierDaoJdbc getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new SupplierDaoJdbc(dataSource);
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {
    }

    @Override
    public Supplier find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, description FROM supplier s " +
                    "WHERE s.id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = conn.createStatement().executeQuery(sql);
            if (!rs.next()) {
                return null;
            }
            return new Supplier(rs.getString(2), rs.getString(3));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {
    }

    @Override
    public List<Supplier> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, description FROM supplier s";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Supplier> result = new ArrayList<>();
            while (rs.next()) {
                Supplier supplier = new Supplier(rs.getString(2), rs.getString(3));
                result.add(supplier);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
