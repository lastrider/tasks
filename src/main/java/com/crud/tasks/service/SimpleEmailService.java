package com.crud.tasks.service;

import com.crud.tasks.domain.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailCreatorService mailCreatorService;


    public void send(Email emile) {
        LOGGER.info("Starting email preparation...");
        try {
            //SimpleMailMessage mailMessage = createMailMessage(emile);
            javaMailSender.send(createMimeMessage(emile));
            LOGGER.info("Email has been sent.");
        } catch (MailException e) {
            LOGGER.error("Failed to process email sending: ", e.getMessage(), e);
        }
    }

    public void send(Email emile, List<String> taskTitles) {
        LOGGER.info("Starting email preparation...");
        try {
            //SimpleMailMessage mailMessage = createMailMessage(emile);
            javaMailSender.send(createMimeMessage(emile, taskTitles));
            LOGGER.info("Email has been sent.");
        } catch (MailException e) {
            LOGGER.error("Failed to process email sending: ", e.getMessage(), e);
        }
    }

    private MimeMessagePreparator createMimeMessage(final Email email) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper((mimeMessage));
            messageHelper.setTo(email.getReceiverEmail());
            messageHelper.setSubject(email.getSubject());
            messageHelper.setText(mailCreatorService.buildTrelloCardEmail(email.getMessage()),true);
        };
    }

    private MimeMessagePreparator createMimeMessage(final Email email,List<String> taskTitles) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper((mimeMessage));
            messageHelper.setTo(email.getReceiverEmail());
            messageHelper.setSubject(email.getSubject());
            messageHelper.setText(mailCreatorService.buildDailyTrelloCardEmail(email.getMessage(),taskTitles),true);
        };
    }

    private SimpleMailMessage createMailMessage(Email email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email.getReceiverEmail());
        mailMessage.setSubject(email.getSubject());
        mailMessage.setText(mailCreatorService.buildTrelloCardEmail(email.getMessage()));
/*        if (email.getToCC() != null) {
            mailMessage.setCc(email.getToCC());
        }*/
        return mailMessage;
    }


}
