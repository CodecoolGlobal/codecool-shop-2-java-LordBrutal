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
import javax.servlet.http.HttpSession;
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
        String items = "";
        if (req.getParameter("cart") != null && !req.getParameter("cart").equals("")) {
            CartService cartservice = new CartService(orderDataStore, productDataStore);
            items = req.getParameter("cart");
            PrintWriter out = resp.getWriter();
            String json = cartservice.addProductsToCart(items);
            out.println(json);
        }

        HttpSession session = req.getSession();
        if(session.getAttribute("userId") != null) {
            int userId = (int) session.getAttribute("userId");

            if(!items.equals("")) {
                if(orderDataStore.hasCart(userId)) {
                    orderDataStore.updateCart(userId);
                } else {
                    orderDataStore.saveCart(userId);
                }
            } else if(orderDataStore.hasCart(userId)) {
                orderDataStore.emptyCart(userId);
            }
        }
    }
}