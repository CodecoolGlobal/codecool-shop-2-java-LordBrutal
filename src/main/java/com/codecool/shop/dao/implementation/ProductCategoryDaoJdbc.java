package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc implements ProductCategoryDao {

    private static ProductCategoryDaoJdbc instance = null;
    private DataSource dataSource;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductCategoryDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static ProductCategoryDaoJdbc getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new ProductCategoryDaoJdbc(dataSource);
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {
    }

    @Override
    public ProductCategory find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, department, description FROM category " +
                    "WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            ProductCategory productCategory = new ProductCategory(rs.getString(2), rs.getString(3), rs.getString(4));
            productCategory.setId(rs.getInt(1));
            return productCategory;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {
    }

    @Override
    public List<ProductCategory> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, department, description  FROM category";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            List<ProductCategory> result = new ArrayList<>();
            while (rs.next()) {
                ProductCategory productCategory = new ProductCategory(rs.getString(2), rs.getString(3), rs.getString(4));
                productCategory.setId(rs.getInt(1));
                result.add(productCategory);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
