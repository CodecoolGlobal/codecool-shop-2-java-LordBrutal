package com.codecool.shop.controller;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoJdbc;
import com.codecool.shop.dao.implementation.UserInfoDaoJdbc;
import com.codecool.shop.config.Hash;
import com.codecool.shop.model.User;
import com.codecool.shop.service.LoginService;
import com.codecool.shop.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginController  extends ServletBaseModel {

    final Logger logger = LoggerFactory.getLogger(RegistrationService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = Hash.get_SHA_512_SecurePassword(req.getParameter("password"));
        LoginService loginService = new LoginService(db, new User(email, password));
        if (loginService.validateLogin()){
            HttpSession session=req.getSession();
            session.setAttribute("email",email);
            UserInfoDaoJdbc userDao = UserInfoDaoJdbc.getInstance(db);
            int userId = userDao.getUserId(email);
            session.setAttribute("userId", userId);
            logger.info("{} user logged in successful!", email);
            OrderDao orderDataStore = OrderDaoJdbc.getInstance(db);
            if(orderDataStore.hasCart(userId)) {
                orderDataStore.loadCart(userId);
            }
        }
        resp.sendRedirect("/");
    }
}
