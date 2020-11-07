package com.crud.tasks.service;

import com.crud.tasks.domain.Email;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    JavaMailSender javaMailSender;

    @Test
    public void shouldSendEmail() {
        //Given
        Email email = new Email("l.leonowicz@gmail.com", "Test subject", "Test message", null);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo("l.leonowicz@gmail.com");
        simpleMailMessage.setSubject("Test subject");
        simpleMailMessage.setText("Test message");

        //When
        simpleEmailService.send(email);

        //Then
        verify(javaMailSender, times(1)).send(simpleMailMessage);
    }

}