package com.codecool.shop.config;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.paymentmodel.CreditCard;
import com.codecool.shop.model.paymentmodel.PayPalAccount;

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
        CreditCardDao creditCardDataStore = CreditCardDaoMem.getInstance();
        PayPalAccountDao payPalAccountDataStore = PayPalAccountDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier xiaomi = new Supplier("Xiaomi", "Phone manufacturer");
        supplierDataStore.add(xiaomi);
        Supplier toshiba = new Supplier("Toshiba", "Japanese phone manufacturer");
        supplierDataStore.add(toshiba);
        Supplier nerf = new Supplier("Nerf", "Gun manufacture");
        supplierDataStore.add(nerf);
        Supplier dirtech = new Supplier("DirTech", "Best gardening equipment provider around the globe");
        supplierDataStore.add(dirtech);
        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory phone = new ProductCategory("Phone", "Hardware", "A device for calling and personal use when moving.");
        productCategoryDataStore.add(phone);
        ProductCategory weapon = new ProductCategory("Weapon", "Military", "It can save your life");
        productCategoryDataStore.add(weapon);
        ProductCategory gardening = new ProductCategory("Gardening", "Household", "Product for outside work");
        productCategoryDataStore.add(gardening);
        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", new BigDecimal("49"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", new BigDecimal("479"), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("Xiaomi 10", new BigDecimal("100"), "USD", "Best phone manufacturer in China, Supports Google", phone, xiaomi));

        productDataStore.add(new Product("Nerf Elite Titan CS-50 ", new BigDecimal("420"), "USD", "Take on targets with the power and size of a giant with Nerf Elite Titan CS-50 blaster!", weapon, nerf));
        productDataStore.add(new Product("Nerf N-Strike Rhino-Fire", new BigDecimal("1500"), "USD", "Win the battle with the most BRUTAL GUN", weapon, nerf));
        productDataStore.add(new Product("Nerf Ultra Select", new BigDecimal("145"), "USD", "Play safe with your kid without hurt him", weapon, nerf));
        productDataStore.add(new Product("Nerf Round", new BigDecimal("10"), "USD", "Best ammunition for battle", weapon, nerf));
        productDataStore.add(new Product("Used Phone", new BigDecimal("50"), "USD", "Barely used", phone, xiaomi));
        productDataStore.add(new Product("Dirt", new BigDecimal("2"), "USD", "A handful of dirt", gardening, dirtech));
        productDataStore.add(new Product("Grass Block", new BigDecimal("5"), "USD", "Plantable grass", gardening, dirtech));
        productDataStore.add(new Product("Bagger 228", new BigDecimal("1000"), "USD", "Handy tool for excavation", gardening, dirtech));
        productDataStore.add(new Product("Shovel", new BigDecimal("3"), "USD", "For smaller gardening projects", gardening, dirtech));
        productDataStore.add(new Product("Leaf", new BigDecimal("3"), "USD", "A leaf", gardening, dirtech));
        productDataStore.add(new Product("Toshiba Handybook", new BigDecimal("50"), "USD", "Toshiba tablet for personal use", tablet, toshiba));
        productDataStore.add(new Product("Toshiba g450", new BigDecimal("30"), "USD", "A bit wacky design", phone, toshiba));

        creditCardDataStore.add(new CreditCard("123456789", "Kis Pista", (byte) 21, (byte)12, (short)123));
        creditCardDataStore.add(new CreditCard("987654321", "Nagy Géza", (byte) 23, (byte) 12, (short)321));

        payPalAccountDataStore.add(new PayPalAccount("kispista", "kispista123"));
        payPalAccountDataStore.add(new PayPalAccount("nagygéza", "nagygéza321"));
    }
}
