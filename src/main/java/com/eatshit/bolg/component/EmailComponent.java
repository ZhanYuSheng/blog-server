package com.eatshit.bolg.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailComponent {

    @Autowired
    private JavaMailSender mailSender;

    public void sendAlertMessage(String address, String message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("1143109382@qq.com");
        mailMessage.setTo(address);
        mailMessage.setSubject(message);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }
}
