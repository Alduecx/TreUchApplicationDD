package ru.nsu.dd.treuch.backend.security.services;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


public class EmailService {

    //private JavaMailSender mailSender;

    public void sendPasswordResetEmail(String to, String token) {
//        String resetLink = "http://localhost:3000/reset-password/" + token;
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject();
//        message.setText();
//        mailSender.send(message);
//        String resetLink = "http://localhost:3000/reset-password/" + token;
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = null;
//        try {
//            helper = new MimeMessageHelper(message, true);
//            helper.setTo(to);
//            helper.setSubject("Reset your password");
//            // Указываем HTML-формат
//            helper.setText("Please click the following link to reset your password:\n" + resetLink, true);
//            mailSender.send(message);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
    }
}