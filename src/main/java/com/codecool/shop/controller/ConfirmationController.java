package com.codecool.shop.controller;


import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoJdbc;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.email.SendMail;
import com.google.gson.Gson;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;


@WebServlet(urlPatterns = {"/confirmation"})
public class ConfirmationController extends ServletBaseModel {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        Properties connection = getConnectionProperties();
        String connectionType = connection.getProperty("dao");
        WebContext context = new WebContext(req, resp, req.getServletContext());
        OrderDao orderDao = OrderDaoMem.getInstance();
        if(connectionType.equals("jdbc")) {
            orderDao = OrderDaoJdbc.getInstance(db);
            HttpSession session = req.getSession();
            if(session.getAttribute("userId") != null) {
                int orderId = (int)session.getAttribute("orderId");
                ((OrderDaoJdbc) orderDao).loadOrder(orderId);
            }
        }
        context.setVariable("order", orderDao);
        engine.process("/payment/confirmation.html", context, resp.getWriter());
        if (orderDao.isPaymentSuccess()){
            SendMail.sendOrderEmail(orderDao.getEmail(),"Web shop Order","Your order is the following", orderDao.getCartItems());
            Gson gson =  new Gson();
            String json = gson.toJson(orderDao) + "\n" ;
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/logFiles/orders.txt",true));
            writer.write(json);
            writer.close();
            orderDao.removeInstance();
        }
    }
}
