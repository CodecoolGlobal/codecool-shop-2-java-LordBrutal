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
        String message = "<div style=\" background-image: linear-gradient(to right top, #ffc200, #abda47, #4be393, #00e2d7, #00d9ff); padding: 10px\"><h1>" + emailMessage + "</h1>";
        for (CartItem cartItem : cartItems) {
            String order = "<h2>Product name: " + cartItem.getName() + "<h3>";
            order += "<h3>Piece: " + String.valueOf(cartItem.getPiece()) + "<h3>";
            order += "<h3>Product total price: " + String.valueOf(cartItem.getPrice() * cartItem.getPiece()) + "<h3>";
            message += order + "\n";
        }
        message += "<div style=\"width:100%; text-align: center\"><h5>Thanks for your order :o</h5></div>";
        message += "</div>";
        return message;
    }

}