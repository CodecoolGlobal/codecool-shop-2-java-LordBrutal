package com.codecool.shop.controller;


import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.OrderDaoJdbc;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;


@WebServlet(urlPatterns = {"/user_information"})
public class OrderDetailsController extends ServletBaseModel {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Properties connection = getConnectionProperties();
        String connectionType = connection.getProperty("dao");

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        HttpSession session=req.getSession();
        if(connectionType.equals("jdbc") && session.getAttribute("email") != null) {
            OrderDaoJdbc orderDao = OrderDaoJdbc.getInstance(db);
            String email = (String)session.getAttribute("email");
            orderDao.loadBillingInfo(email);
            context.setVariable("info", orderDao);
            engine.process("/payment/user_details_form.html", context, resp.getWriter());
        }else {
            resp.sendRedirect("/");
        }
    }
}
