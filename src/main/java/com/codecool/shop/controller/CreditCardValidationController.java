package com.codecool.shop.controller;

import com.codecool.shop.dao.CreditCardDao;
import com.codecool.shop.dao.implementation.CreditCardDaoMem;
import com.codecool.shop.model.paymentmodel.CreditCard;
import com.codecool.shop.service.PaymentValidationService;
import com.codecool.shop.service.RequestHandlerService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/payment/validation/credit-card"})
public class CreditCardValidationController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("email") != null) {
            CreditCardDao creditCardDataStore = CreditCardDaoMem.getInstance();
            PaymentValidationService paymentValidationService = new PaymentValidationService(creditCardDataStore);
            RequestHandlerService requestHandlerService = new RequestHandlerService(request);
            PrintWriter out = response.getWriter();

            String requestBody = requestHandlerService.readRequestBody();

            CreditCard creditCard = new Gson().fromJson(requestBody, CreditCard.class);
            var validCreditCard = paymentValidationService.validateCreditCard(creditCard);
            out.println(validCreditCard);
        }
        else response.sendRedirect("/");
    }
}
