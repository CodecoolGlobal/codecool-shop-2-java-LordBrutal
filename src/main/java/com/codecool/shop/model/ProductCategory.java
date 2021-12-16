package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductCategory extends BaseModel {
    private String department;
    private List<Product> products;

    public ProductCategory(String name, String department, String description) {
        super(name);
        this.department = department;
        this.products = new ArrayList<>();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public List<Product> getFilteredProducts(List<Product> filter) {
        return filter.stream()
                .filter(p -> p.getProductCategory().getName().equals(this.getName()))
                        .collect(Collectors.toList());
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    @Override
    public String toString() {
        return String.format(
                "id: %1$d," +
                        "name: %2$s, " +
                        "department: %3$s, " +
                        "description: %4$s",
                this.id,
                this.name,
                this.department,
                this.description);
    }
}