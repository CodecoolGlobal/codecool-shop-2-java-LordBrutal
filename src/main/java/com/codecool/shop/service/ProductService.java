package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService{
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
    private String daoType;

    public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao, String daoType) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
        this.daoType = daoType;
    }

    public List<Product> getProductsForCategory(int categoryId){
        if(daoType.equals("memory")) {
            ProductCategory category = productCategoryDao.find(categoryId);
            return productDao.getBy(category);
        } else {
            return productDao.getBy(categoryId);
        }
    }

    public List<Product> getAllProducts(){
        return productDao.getAll();
    }

    public List<Product> filterProducts(String categoryParameter, String supplyParameter, SupplierDao supplierDataStore) {
        List<Product> products = new ArrayList<>();
        if(categoryParameter != null || supplyParameter != null) {
            if(!categoryParameter.equals("")) {
                int categoryId = Integer.parseInt(categoryParameter);
                products = getProductsForCategory(categoryId);
            }
            if(!supplyParameter.equals("")){
                int supplyId = Integer.parseInt(supplyParameter);
                if (products.size() > 0) {  // both filters used (remove items that are not in supply filter)
                    return products.stream()
                            .filter(op -> op.getSupplier().getId() == supplyId)
                            .collect(Collectors.toList());
                } else {
                    return productDao.getBy(supplierDataStore.find(supplyId));  //only supplier filter used
                }
            }
        } else {
            return getAllProducts();    // default main page load
        }
        return products;
    }

}
