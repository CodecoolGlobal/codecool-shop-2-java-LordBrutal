package com.codecool.shop.controller;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;
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

        if (req.getParameter("cart") != null) {
            String items = req.getParameter("cart");
            System.out.println(items);

            PrintWriter out = resp.getWriter();

            ProductDao productDataStore = ProductDaoMem.getInstance();

            Gson gson = new Gson();
            Type merchantListType = new TypeToken<List<CartItem>>() {
            }.getType();
            List<CartItem> cartItems = gson.fromJson(String.valueOf(items), merchantListType);

            StringBuilder json = new StringBuilder(); //TODO: Refactor this
            json.append("[");
            for (CartItem cartItem : cartItems) {
                Product product = productDataStore.find(cartItem.getId());
                int finalPrice = cartItem.getPiece() * Integer.parseInt(String.valueOf(product.getDefaultPrice()));
                json.append("{");
                json.append("\"");
                json.append("id");
                json.append("\":");
                json.append("\"");
                json.append(product.getId());
                json.append("\"");
                json.append(",");
                json.append("\"");
                json.append("name");
                json.append("\":");
                json.append("\"");
                json.append(product.getName());
                json.append("\"");
                json.append(",");
                json.append("\"");
                json.append("pieces");
                json.append("\":");
                json.append("\"");
                json.append(cartItem.getPiece());
                json.append("\"");
                json.append(",");
                json.append("\"");
                json.append("price");
                json.append("\":");
                json.append("\"");
                json.append(finalPrice);
                json.append("\"");
                json.append("}");
                json.append(",");
            }


            json.append("]");
            json.deleteCharAt(json.length() - 2);

            out.println(json);

        }
    }
}