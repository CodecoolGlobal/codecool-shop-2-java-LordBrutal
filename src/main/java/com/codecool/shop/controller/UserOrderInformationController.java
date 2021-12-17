package com.codecool.shop.controller;


import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoJdbc;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;


@WebServlet(urlPatterns = {"/user_information/save"})
public class UserOrderInformationController extends ServletBaseModel {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Properties connection = getConnectionProperties();
        String connectionType = connection.getProperty("dao");
        OrderDao orderDao = OrderDaoMem.getInstance();
        if(connectionType.equals("jdbc")) {
            orderDao = OrderDaoJdbc.getInstance(db);
        }
        orderDao.setName(req.getParameter("name"));
        orderDao.setEmail(req.getParameter("email"));
        orderDao.setPhoneNumber(req.getParameter("phone-number"));
        orderDao.setBillingAddress(req.getParameter("billing-address"));
        orderDao.setShippingAddress(req.getParameter("shipping-address"));

        HttpSession session = req.getSession();
        if(session.getAttribute("userId") != null && connectionType.equals("jdbc")) {
            int userId = (int)session.getAttribute("userId");
            ((OrderDaoJdbc) orderDao).updateBillingInfo(userId);
            if(orderDao.hasCart(userId)) {
                orderDao.updateCart(userId);
            } else {
                orderDao.saveCart(userId);
            }
            int orderId = orderDao.saveOrder(userId);
            session.setAttribute("orderId", orderId);
        }

        resp.sendRedirect("/payment");
    }
}
