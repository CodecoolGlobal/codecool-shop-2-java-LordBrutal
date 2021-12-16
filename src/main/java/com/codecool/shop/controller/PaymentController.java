package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoJdbc;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;


@WebServlet(urlPatterns = {"/payment"})
public class PaymentController extends ServletBaseModel {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        Properties connection = getConnectionProperties();
        String connectionType = connection.getProperty("dao");

        OrderDao orderInfo = OrderDaoMem.getInstance();
        if(connectionType.equals("jdbc")) {
            orderInfo = OrderDaoJdbc.getInstance(db);
        }
        context.setVariable("order", orderInfo);

        engine.process("/payment/payment.html", context, resp.getWriter());
    }
}
