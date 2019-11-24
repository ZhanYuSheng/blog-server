package com.eatshit.bolg.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailComponent {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.address}")
    private String mailAddress;


    public void sendMessage(String address,String subject, String message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mailAddress);
        mailMessage.setTo(address);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }
}
