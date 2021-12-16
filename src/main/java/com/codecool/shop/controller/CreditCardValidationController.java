package com.codecool.shop.controller;

import com.codecool.shop.dao.CreditCardDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.CreditCardDaoJdbc;
import com.codecool.shop.dao.implementation.CreditCardDaoMem;
import com.codecool.shop.dao.implementation.OrderDaoJdbc;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.paymentmodel.CreditCard;
import com.codecool.shop.service.PaymentValidationService;
import com.codecool.shop.service.RequestHandlerService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

@WebServlet(urlPatterns = {"/payment/validation/credit-card"})
public class CreditCardValidationController extends ServletBaseModel {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("email") != null) {
            CreditCardDao creditCardDataStore = CreditCardDaoMem.getInstance();
            Properties connection = getConnectionProperties();
            String connectionType = connection.getProperty("dao");
            OrderDao orderDataStore = OrderDaoMem.getInstance();

            if(connectionType.equals("jdbc")) {
                creditCardDataStore = CreditCardDaoJdbc.getInstance(db);
                orderDataStore = OrderDaoJdbc.getInstance(db);
            }
            PaymentValidationService paymentValidationService = new PaymentValidationService(creditCardDataStore);

            RequestHandlerService requestHandlerService = new RequestHandlerService(request);
            PrintWriter out = response.getWriter();
            String requestBody = requestHandlerService.readRequestBody();

            CreditCard creditCard = new Gson().fromJson(requestBody, CreditCard.class);
            var validCreditCard = paymentValidationService.validateCreditCard(creditCard);
            if(validCreditCard) {
                orderDataStore.setPaymentSuccess();
                if(connectionType.equals("jdbc")){
                    int userId = (int) session.getAttribute("userId");
                    int orderId = (int)session.getAttribute("orderId");
                    ((OrderDaoJdbc) orderDataStore).loadOrder(orderId);
                    orderDataStore.setPaymentSuccess();
                    ((OrderDaoJdbc) orderDataStore).saveOrder(userId);
                    ((OrderDaoJdbc) orderDataStore).emptyCart(userId);
                    ((OrderDaoJdbc)orderDataStore).saveOrder(userId);
                }
            }
            out.println(validCreditCard);
        }else response.sendRedirect("/");

    }
}
