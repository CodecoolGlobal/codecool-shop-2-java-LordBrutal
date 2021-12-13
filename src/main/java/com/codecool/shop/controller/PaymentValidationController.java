package com.codecool.shop.controller;

import com.codecool.shop.dao.CreditCardDao;
import com.codecool.shop.dao.PayPalAccountDao;
import com.codecool.shop.dao.implementation.CreditCardDaoMem;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.PayPalAccountDaoMem;
import com.codecool.shop.model.paymentmodel.CreditCard;
import com.codecool.shop.model.paymentmodel.PayPalAccount;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = {"/payment/validation"})
public class PaymentValidationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CreditCardDao creditCardDataStore = CreditCardDaoMem.getInstance();
        PayPalAccountDao payPalAccountDataStore = PayPalAccountDaoMem.getInstance();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        var requestParameterCard = request.getParameter("card_infos");
        var requestParameterPayPal = request.getParameter("paypal_infos");

        PrintWriter out = response.getWriter();
        OrderDaoMem orderDao = OrderDaoMem.getInstance();
        if (requestParameterCard!=null) {
            CreditCard creditCard = new Gson().fromJson(requestParameterCard, CreditCard.class);
            CreditCard validCreditCard = creditCardDataStore.findCard(creditCard.getCardNumber());
            if (validCreditCard == null) {
                out.println(false);
            } else {
                if(creditCard.equals(validCreditCard)){
                    orderDao.setPaymentSuccess();
                }
                out.println(creditCard.equals(validCreditCard));

            }
        }
        else if (requestParameterPayPal!=null) {
            PayPalAccount payPalAccount = new Gson().fromJson(requestParameterPayPal, PayPalAccount.class);
            PayPalAccount validAccount = payPalAccountDataStore.findAccount(payPalAccount.getUsername());
            if (validAccount == null) {
                out.println(false);
            } else {
                if(payPalAccount.equals(validAccount)){
                    orderDao.setPaymentSuccess();
                }
                out.println(payPalAccount.equals(validAccount));
            }
        }
    }
}
