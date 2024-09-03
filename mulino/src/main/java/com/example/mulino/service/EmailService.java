package com.example.mulino.service;

import com.example.mulino.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private Environment environment;



    public void sendReservationConfirmation(Reservation reservation) throws MessagingException {
        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true);

        helper.setTo(reservation.getEmail());
        helper.setFrom(environment.getProperty("spring.mail.username"));
        helper.setSubject("Your Table Reservation at Mulino");

        String content = "<html>" +
                "<body>" +
                "<p>Hi " + reservation.getGuestName() + ",</p>" +
                "<p>We are pleased to confirm your table reservation at <strong>Mulino</strong> restaurant.</p>" +
                "<p>Here are your reservation details:</p>" +
                "<ul>" +
                "<li><strong>Date and Time:</strong> " + reservation.getReservationDate().toString() + "</li>" +
                "<li><strong>Number of People:</strong> " + reservation.getNumberOfPeople() + "</li>" +
                "<li><strong>Table Number:</strong> " + reservation.getTable() + "</li>" +
                "</ul>" +
                "<p>We look forward to serving you!</p>" +
                "<br><p>Thank you," +
                "<br><span style=\"font-style:italic; font-weight:bold\">Mulino Restaurant</span></p>" +
                "<hr>" +
                "<p>&copy;2024 <span style=\"font-style:italic;\">Mulino Restaurant</span>. All rights reserved.</p>" +
                "</body>" +
                "</html>";

        helper.setText(content, true);

        javaMailSender.send(mail);
    }

    public void sendSubscriptionMail(String email) throws MessagingException{
        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true);

        helper.setTo(email);
        helper.setFrom(environment.getProperty("spring.mail.username"));
        helper.setSubject("Subscription confirmation");

        String content = "<html>" +
                "<body>" +
               "<p>Thank you for subscribing to our newsletter! We're thrilled to have you as part of our community.</p>" +
                "<p> At Mulino Restaurant, we believe in creating memorable dining experiences filled with delicious food, exceptional service, and a warm, welcoming atmosphere. " +
                "As a subscriber, you'll be the first to know about our latest menu updates, special offers, exclusive events, and much more.</p>" +
                "<p>For making reservations visit our website at http://localhost:3000/.</p>" +
                "<p>We look forward to serving you!</p>" +
                "<br><p>Thank you," +
                "<br><span style=\"font-style:italic; font-weight:bold\">Mulino Restaurant</span></p>" +
                "<hr>" +
                "<p>&copy;2024 <span style=\"font-style:italic;\">Mulino Restaurant</span>. All rights reserved.</p>" +
                "</body>" +
                "</html>";

        helper.setText(content, true);

        javaMailSender.send(mail);
    }
}