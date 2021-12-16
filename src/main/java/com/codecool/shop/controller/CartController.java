package com.codecool.shop.controller;


import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.OrderDaoJdbc;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoJdbc;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.service.CartService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;


@WebServlet(urlPatterns = {"/cart"})
public class CartController extends ServletBaseModel {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Properties connection = getConnectionProperties();
        String connectionType = connection.getProperty("dao");

        OrderDao orderDataStore = OrderDaoMem.getInstance();
        ProductDao productDataStore = ProductDaoMem.getInstance();

        if(connectionType.equals("jdbc")) {
            orderDataStore = OrderDaoJdbc.getInstance(db);
            productDataStore = ProductDaoJdbc.getInstance(db);
        }
        CartService cartservice = new CartService(orderDataStore, productDataStore);

        if (req.getParameter("cart") != null) {
            String items = req.getParameter("cart");
            PrintWriter out = resp.getWriter();
            String json = cartservice.addProductsToCart(items);
            out.println(json);
        }
    }
}