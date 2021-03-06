package com.crud.tasks.service;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.trello.config.AdminConfig;
import com.crud.tasks.trello.config.CompanyInfoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyInfoConfig companyInfo;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("button", "Visit website");
        //context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("preview_message", adminConfig.getAdminName() + " We have wonderful news for you!!!");
        context.setVariable("goodbye_message", "See you later!");
        context.setVariable("company_details", companyInfo.getMessage());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildDailyTrelloCardEmail(String message,List<String> tasksTitles) {

        Context context = new Context();
        context.setVariable("preview_message", adminConfig.getAdminName() + " We have wonderful news for you!!!");
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://lastrider.github.io");
        context.setVariable("button", "Visit website to see all details");
        context.setVariable("tasksTitles", tasksTitles);
        context.setVariable("goodbye_message", "See you later!");
        context.setVariable("company_details", companyInfo.getMessage());
        context.setVariable("show_button", (tasksTitles.size()>0));
        context.setVariable("admin_config", adminConfig);
        return templateEngine.process("mail/daily-trello-report-mail", context);
    }
}
