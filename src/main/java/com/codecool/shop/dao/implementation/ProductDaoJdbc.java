package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import jdk.jfr.Category;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDaoJdbc implements ProductDao {

    private List<Product> data = new ArrayList<>();
    private static ProductDaoJdbc instance = null;
    private final DataSource dataSource;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static ProductDaoJdbc getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new ProductDaoJdbc(dataSource);
        }
        return instance;
    }

    @Override
    public void add(Product product) {}

    @Override
    public Product find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {}

    @Override
    public List<Product> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT p.id, p.name, price, currency, p.description, c.name, c.description, c.department, s.name, s.description FROM product p " +
                    "LEFT JOIN category c ON p.category_id = c.id " +
                    "LEFT JOIN supplier s on p.supplier_id = s.id";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Product> result = new ArrayList<>();
            while (rs.next()) { // while result set pointer is positioned before or on last row read authors
                ProductCategory cat = new ProductCategory(rs.getString(6), rs.getString(7), rs.getString(8));
                Supplier sup = new Supplier(rs.getString(9), rs.getString(10));
                Product product = new Product(rs.getString(2), BigDecimal.valueOf(rs.getInt(3)), rs.getString(4), rs.getString(5), cat, sup);
                product.setId(rs.getInt(1));
                result.add(product);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all authors", e);
        }
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return data.stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList());
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return data.stream().filter(t -> t.getProductCategory().equals(productCategory)).collect(Collectors.toList());
    }
}
