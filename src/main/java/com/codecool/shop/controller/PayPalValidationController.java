package com.codecool.shop.controller;

import com.codecool.shop.dao.PayPalAccountDao;
import com.codecool.shop.dao.implementation.PayPalAccountDaoJdbc;
import com.codecool.shop.dao.implementation.PayPalAccountDaoMem;
import com.codecool.shop.model.paymentmodel.PayPalAccount;
import com.codecool.shop.service.PaymentValidationService;
import com.codecool.shop.service.RequestHandlerService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;


@WebServlet(urlPatterns = {"/payment/validation/paypal"})
public class PayPalValidationController extends ServletBaseModel {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Properties connection = getConnectionProperties();
        String connectionType = connection.getProperty("dao");
        RequestHandlerService requestHandlerService = new RequestHandlerService(request);
        PrintWriter out = response.getWriter();

        PayPalAccountDao payPalAccountDataStore = PayPalAccountDaoMem.getInstance();
        if(connectionType.equals("jdbc")) {
            payPalAccountDataStore = PayPalAccountDaoJdbc.getInstance(db);
        }
        PaymentValidationService paymentValidationService = new PaymentValidationService(payPalAccountDataStore);

        String requestBody = requestHandlerService.readRequestBody();

        PayPalAccount payPalAccount = new Gson().fromJson(requestBody, PayPalAccount.class);
        var validAccount = paymentValidationService.validatePayPalAccount(payPalAccount);
        out.println(validAccount);
    }
}
