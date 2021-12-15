package com.codecool.shop.controller;

import com.codecool.shop.dao.CreditCardDao;
import com.codecool.shop.dao.PayPalAccountDao;
import com.codecool.shop.dao.implementation.CreditCardDaoMem;
import com.codecool.shop.dao.implementation.PayPalAccountDaoMem;
import com.codecool.shop.model.paymentmodel.CreditCard;
import com.codecool.shop.model.paymentmodel.PayPalAccount;
import com.codecool.shop.service.PaymentValidationService;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = {"/payment/validation"})
public class PaymentValidationController extends ServletBaseModel {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CreditCardDao creditCardDataStore = CreditCardDaoMem.getInstance(db);
        PayPalAccountDao payPalAccountDataStore = PayPalAccountDaoMem.getInstance(db);
        PaymentValidationService paymentValidationService = new PaymentValidationService(creditCardDataStore, payPalAccountDataStore, db);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        var requestParameterCard = request.getParameter("card_infos");
        var requestParameterPayPal = request.getParameter("paypal_infos");

        PrintWriter out = response.getWriter();
        if (requestParameterCard!=null) {
            CreditCard creditCard = new Gson().fromJson(requestParameterCard, CreditCard.class);
            var validCreditCard = paymentValidationService.validateCreditCard(creditCard);
            out.println(validCreditCard);
        }
        else if (requestParameterPayPal!=null) {
            PayPalAccount payPalAccount = new Gson().fromJson(requestParameterPayPal, PayPalAccount.class);
            var validAccount = paymentValidationService.validatePayPalAccount(payPalAccount);
                out.println(validAccount);
        }
    }
}
