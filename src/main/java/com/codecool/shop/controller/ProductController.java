package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends ServletBaseModel {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        String categoryParameter = req.getParameter("categories");
        String supplyParameter = req.getParameter("supplier");

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        ProductService productService;
        List<Product> products = new ArrayList<>();

        if(true) {
            productDataStore = ProductDaoJdbc.getInstance(db);
            productCategoryDataStore = ProductCategoryDaoJdbc.getInstance(db);
            supplierDataStore = SupplierDaoJdbc.getInstance(db);
        }

        productService = new ProductService(productDataStore,productCategoryDataStore);
        products = productService.filterProducts(categoryParameter, supplyParameter, supplierDataStore);

        context.setVariable("products", products);
        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("suppliers", supplierDataStore.getAll());
        engine.process("product/index.html", context, resp.getWriter());
    }

}
