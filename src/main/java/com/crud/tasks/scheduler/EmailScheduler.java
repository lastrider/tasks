package com.crud.tasks.scheduler;

import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {
    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    ScheduledEmail scheduledEmail;

    //@Scheduled(fixedDelay = 10000)
    @Scheduled(cron = "0 0 * * * *")
    public void sendInformationEmail() {
        simpleEmailService.send(scheduledEmail.getEmail());
    }
}
