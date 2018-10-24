package com.autoinsurance;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

@Component
public class EventListener {

    @StreamListener(Streams.INPUT)
    public void handle(@Payload CustomerRegisteredEvent orderPlacedEvent) {

        System.out.println("ssn for new customer: " + orderPlacedEvent.getCouponNumber());

//        String to = orderPlacedEvent.getEmailAddress();
//        String from = "web@gmail.com";
//        String host = "localhost";
//
//        Properties properties = System.getProperties();
//        properties.setProperty("mail.smtp.host", host);
//
//        Session session = Session.getDefaultInstance(properties);
//
//        try {
//            MimeMessage message = new MimeMessage(session);
//
//            message.setFrom(new InternetAddress(from));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//            message.setSubject("Congratulations!");
//            message.setText("Your registration successfully done.");
//
//            Transport.send(message);
//            System.out.println("Sent message successfully....");
//        } catch (MessagingException mex) {
//            mex.printStackTrace();
//        }
    }

}
