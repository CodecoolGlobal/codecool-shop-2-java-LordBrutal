package com.codecool.shop.controller;

import com.codecool.shop.model.UserModel;
import com.codecool.shop.service.LoginService;
import com.codecool.shop.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
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
        String password = req.getParameter("password");
        LoginService loginService = new LoginService(db, new UserModel(email, password));
        if (loginService.validetaLogint()){
            HttpSession session=req.getSession();
            session.setAttribute("email",email);
            logger.info("{} user logged in successful!", email);
        }
        resp.sendRedirect("/");
    }
}
