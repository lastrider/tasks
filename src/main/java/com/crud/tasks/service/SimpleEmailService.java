package com.crud.tasks.service;

import com.crud.tasks.domain.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SimpleEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);

    @Autowired
    private JavaMailSender javaMailSender;

    public void send(Email emile) {
        LOGGER.info("Starting email preparation...");
        try {
            SimpleMailMessage mailMessage = createMailMessage(emile);
            javaMailSender.send(mailMessage);
            LOGGER.info("Email has been sent.");
        } catch (MailException e) {
            LOGGER.error("Faild to process email sending: ", e.getMessage(), e);
        }
    }

    private SimpleMailMessage createMailMessage(Email email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email.getReceiverEmail());
        mailMessage.setSubject(email.getSubject());
        mailMessage.setText(email.getMessage());
        if (email.getToCC() != null) {
            mailMessage.setCc(email.getToCC());
        }
        return mailMessage;
    }
}
