package com.eatshit.bolg.component;

import com.eatshit.bolg.entity.Mail;
import com.eatshit.bolg.mapper.MailMapper;
import com.eatshit.bolg.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class Timer {
    @Autowired
    private MailMapper mailMapper;
    @Autowired
    private MailComponent mailComponent;

    @Scheduled(cron = "0/1 * * * * ?")
    public void sendMail(){
        List<Mail> mailList = mailMapper.selectByState(Constant.MAIL_NOT_SEND);
        mailList.forEach(
                mail -> {
                    mailComponent.sendMessage(mail.getAddress(), mail.getSubject(), mail.getMessage());
                    mail.setState(Constant.MAIL_SEND_SUCCESS);
                    mailMapper.updateState(mail);
                }
        );
    }
}
