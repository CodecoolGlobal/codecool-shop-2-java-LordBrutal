package com.codecool.shop.email;

import com.codecool.shop.model.CartItem;

import java.util.List;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {

    public static void sendOrderEmail(String emailTo, String emailHeader, String emailMessage, List<CartItem> cartItems) {

        // Recipient's email ID needs to be mentioned.
        String to = emailTo;

        // Sender's email ID needs to be mentioned
        String from = "kispistashop@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("kispistashop@gmail.com", "Ki$cica123");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(emailHeader);

            // Now set the actual message
            //message.setText(orderBuilder(emailMessage, cartItems));

            message.setContent(orderBuilder(emailMessage, cartItems), "text/html" );
            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
    public static String orderBuilder(String emailMessage, List<CartItem> cartItems){
        StringBuilder message = new StringBuilder("<div style=\"background-image:linear-gradient(to right top,#ffc200,#abda47,#4be393,#00e2d7,#00d9ff);padding:10px; font-family: sans-serif;\">");
        message.append("<h1 style=\"text-align: center\">").append(emailMessage).append("</h1>");
        for (CartItem cartItem : cartItems) {
            String productName = cartItem.getName();
            String productPiece = String.valueOf(cartItem.getPiece());
            String productPrice = String.valueOf(cartItem.getPrice());
            String productTotalPrice = String.valueOf(cartItem.getPrice() * cartItem.getPiece());
            String textDiv = "<div style=\"background-color: rgba(255,255,255,0.25);display:inline-table; margin-bottom: 20px; text-align: left; margin-right: 20px;\">";
            textDiv += "<p style=\"font-size: 20px; font-weight: bold; margin: 0 10px 0 10px\">Product name: " + productName + "</p>";
            textDiv += "<p style=\"font-size: 18px;margin: 5px 10px 0 10px\">Piece: " + productPiece + "</p>";
            textDiv += "<p style=\"font-size: 18px;margin: 0 10px 0 10px\">Product priece: " + productPrice + " USD</p>";
            textDiv += "<p style=\"font-size: 18px;margin: 0 10px 0 10px\">Product total price: <samp style=\"color: red; font-weight: bold\">" + productTotalPrice + "</samp> USD</p>";
            textDiv += "</div>";
            message.append(textDiv);
        }
        message.append("</div>");
        message.append("<div style=\"width:100%;text-align:center;font-size: 20px; background-color: rgba(51,51,51,0.74); margin-top: -28px; color: white;font-family: sans-serif\"><h5>Thanks for your order :o</h5></div>");
        return message.toString();
    }

}