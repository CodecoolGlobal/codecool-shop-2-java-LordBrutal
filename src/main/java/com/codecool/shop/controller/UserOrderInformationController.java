package com.codecool.shop.controller;


import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoJdbc;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;


@WebServlet(urlPatterns = {"/user_information/save"})
public class UserOrderInformationController extends ServletBaseModel {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Properties connection = getConnectionProperties();
        String connectionType = connection.getProperty("dao");
        if(connectionType.equals("jdbc")) {
            OrderDao orderDao = OrderDaoJdbc.getInstance(db);
        } else {
            OrderDaoMem orderDaoMem = OrderDaoMem.getInstance();
            orderDaoMem.setName(req.getParameter("name"));
            orderDaoMem.setEmail(req.getParameter("email"));
            orderDaoMem.setPhoneNumber(req.getParameter("phone-number"));
            orderDaoMem.setBillingAddress(req.getParameter("billing-address"));
            orderDaoMem.setShippingAddress(req.getParameter("shipping-address"));
        }

        resp.sendRedirect("/payment");
    }
}
