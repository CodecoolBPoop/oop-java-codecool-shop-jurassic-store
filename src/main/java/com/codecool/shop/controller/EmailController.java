package com.codecool.shop.controller;// File Name SendEmail.java
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

@WebServlet(urlPatterns = {"/datasave"})
public class EmailController extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");

        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");
        final String username = "jurassicwebshop@gmail.com";//
        final String password = "Jurassicpark1";
        try{
            Session session = Session.getDefaultInstance(props,
                    new Authenticator(){
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }});

            // -- Create a new message --
            Message msg = new MimeMessage(session);

            // -- Set the FROM and TO fields --
            msg.setFrom(new InternetAddress("jurassicwebshop@gmail.com"));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email,false));
            msg.setSubject("Webshop Purchase Confirmed");
            msg.setText("Dear Mr/Mrs " + firstName + " " + lastName + "!" +
                    " \n\nDo you think that dinosaurs are real? \n" +
                    " Oh my god, how childish you are. Anyway, your order is on the way to:\n "+zip + ", " + city +", "+ state + ".\n\n" +
                    " God is watching you, never forget that.");
            msg.setSentDate(new Date());
            Transport.send(msg);
            System.out.println("Message sent.");
            ShoppingCartDaoMem.getInstance().removeAll();
        }catch (MessagingException e){ System.out.println("Erreur d'envoi, cause: " + e);}
        response.sendRedirect("/");
    }
}