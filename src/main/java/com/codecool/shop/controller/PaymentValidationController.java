package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CreditCardDao;
import com.codecool.shop.dao.implementation.CreditCardDaoMem;
import com.codecool.shop.model.paymentmodel.CreditCard;
import com.google.gson.Gson;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;


@WebServlet(urlPatterns = {"/payment/validation"})
public class PaymentValidationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CreditCardDao creditCardDataStore = CreditCardDaoMem.getInstance();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();


        CreditCard creditCard = new Gson().fromJson(request.getParameter("card_infos"), CreditCard.class);
        if (creditCardDataStore.findCard(creditCard.getCardNumber()) == null) {
            out.println(false);
        }
        else {
            if (Objects.equals(creditCard.toString(), creditCardDataStore.findCard(creditCard.getCardNumber()).toString())) {
                out.println(true);
            }
        }
    }
}
