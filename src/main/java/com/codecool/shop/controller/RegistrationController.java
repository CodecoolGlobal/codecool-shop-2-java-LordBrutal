package com.codecool.shop.controller;

import com.codecool.shop.config.Hash;
import com.codecool.shop.model.User;
import com.codecool.shop.service.RegistrationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = {"/registration"})
public class RegistrationController  extends ServletBaseModel {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (getConnectionProperties().getProperty("dao").equals("jdbc")) {
            String email = req.getParameter("email");
            String password = Hash.get_SHA_512_SecurePassword(req.getParameter("password"));
            User user = new User(email, password);
            RegistrationService registrationService = new RegistrationService(db, user);
            List<String> users = registrationService.getAllUser();
             if (users.contains(email)){
                 System.out.println("used");
                 resp.sendRedirect("/");
             }
             else {
                 registrationService.addNewUser();
             }
            resp.sendRedirect("/");


        }
    }

}
