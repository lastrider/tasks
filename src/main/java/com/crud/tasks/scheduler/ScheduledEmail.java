package com.crud.tasks.scheduler;

import com.crud.tasks.domain.Email;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.trello.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ScheduledEmail {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    public Email getEmail() {
        final long size = taskRepository.count();
        return new Email(
                adminConfig.getAdminMail(),
                "Tasks: Once a day email",
                "Currently in database you got: " + size + (size == 1 ? " task" : " tasks"),
                null
        );
    }

    public List<String> getTaskTitles() {
        return taskRepository.findAll().stream().map(task -> task.getTitle()).collect(Collectors.toList());
    }

}
