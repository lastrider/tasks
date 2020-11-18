package com.crud.tasks.trello.config;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CompanyInfoConfig {

    @Value("${info.app.company.name}")
    private String companyName;

    @Value("${info.app.company.email}")
    private String companyEmil;

    @Value("${info.app.company.goal}")
    private String companyGoal;

    public String getMessage() {
        return companyName + "\n" + companyEmil + "\n" + companyGoal;
    }
}
