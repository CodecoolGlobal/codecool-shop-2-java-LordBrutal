package com.codecool.shop.controller;

import com.codecool.shop.config.Hash;
import com.codecool.shop.model.User;
import com.codecool.shop.service.RegistrationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/registration"})
public class RegistrationController  extends ServletBaseModel {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (getConnectionProperties().getProperty("dao").equals("jdbc")) {
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            User user = new User(email, Hash.get_SHA_512_SecurePassword(password));
            RegistrationService registrationService = new RegistrationService(db, user);
            registrationService.addNewUser();
            resp.sendRedirect("/");

        }
    }

}
