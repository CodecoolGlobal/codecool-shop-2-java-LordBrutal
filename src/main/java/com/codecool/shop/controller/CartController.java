package com.codecool.shop.controller;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.CartService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        OrderDaoMem orderDaoMem = OrderDaoMem.getInstance();
        ProductDao productDataStore = ProductDaoMem.getInstance();
        CartService cartservice = new CartService(orderDaoMem, productDataStore);

        if (req.getParameter("cart") != null) {
            String items = req.getParameter("cart");
            PrintWriter out = resp.getWriter();
            String json = cartservice.addProductsToCart(items);
            out.println(json);
        }
    }
}