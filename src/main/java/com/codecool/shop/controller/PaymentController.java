package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(urlPatterns = {"/payment"})
public class PaymentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("email") != null) {
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());
            OrderDaoMem orderInfo = OrderDaoMem.getInstance();
            context.setVariable("order", orderInfo);

            engine.process("/payment/payment.html", context, resp.getWriter());
        }
        else resp.sendRedirect("/");
    }
}
