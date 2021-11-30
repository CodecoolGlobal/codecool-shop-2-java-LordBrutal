package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier xiaomi = new Supplier("Xiaomi", "Phone manufacturer");
        supplierDataStore.add(xiaomi);
        Supplier nerf = new Supplier("Nerf", "Gun manufacture");
        supplierDataStore.add(nerf);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory phone = new ProductCategory("Phone", "Hardware", "A device for calling and personal use when moving.");
        productCategoryDataStore.add(phone);
        ProductCategory weapon = new ProductCategory("Weapon", "Military", "It can save your life");
        productCategoryDataStore.add(weapon);
        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", new BigDecimal("49.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", new BigDecimal("479"), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("Xiaomi 10", new BigDecimal("100"), "USD", "Best phone manufacturer in China, Supports Google", phone, xiaomi));
        productDataStore.add(new Product("Nerf Elite Titan CS-50 ", new BigDecimal("420"), "USD", "Take on targets with the power and size of a giant with Nerf Elite Titan CS-50 blaster!", weapon, nerf));
        productDataStore.add(new Product("Nerf N-Strike Rhino-Fire", new BigDecimal("1500"), "USD", "Win the battle with the most BRUTAL GUN", weapon, nerf));
        productDataStore.add(new Product("Nerf Ultra Select", new BigDecimal("145"), "USD", "Play safe with your kid without hurt him", weapon, nerf));
        productDataStore.add(new Product("Nerf Round", new BigDecimal("10"), "USD", "Best ammunition for battle", weapon, nerf));
    }
}
