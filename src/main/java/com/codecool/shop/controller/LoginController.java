package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.UserInfoDaoJdbc;
import com.codecool.shop.model.UserModel;
import com.codecool.shop.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginController  extends ServletBaseModel {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        LoginService loginService = new LoginService(db, new UserModel(email, password));
        if (loginService.validateLogin()){
            HttpSession session=req.getSession();
            session.setAttribute("email",email);
            UserInfoDaoJdbc userDao = UserInfoDaoJdbc.getInstance(db);
            int userId = userDao.getUserId(email);
            session.setAttribute("userId", userId);
        }
        resp.sendRedirect("/");
    }
}
