package com.codecool.shop.controller;


import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.email.SendMail;
import com.google.gson.Gson;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


@WebServlet(urlPatterns = {"/confirmation"})
public class ConfirmationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        OrderDaoMem orderDao = OrderDaoMem.getInstance();
        if (orderDao.isPaymentSuccess()){
            SendMail.sendOrderEmail(orderDao.getEmail(),"Web shop Order","Your order is the following", orderDao.getCartItems());
        }
        context.setVariable("order", orderDao);
        engine.process("/payment/confirmation.html", context, resp.getWriter());
        Gson gson =  new Gson();
        String json = gson.toJson(orderDao);
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/logFiles/orders.txt"));
        writer.write(json);
        writer.close();
        orderDao.removeInstance();
    }
}
