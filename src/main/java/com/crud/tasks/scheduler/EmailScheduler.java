package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    private final static String SUBJECT = "Tasks: Once a day email";

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

/*    @Scheduled(fixedDelay = 10000)*/
    public void sendInformationEmail() {
        long size = taskRepository.count();
        if (size == 1) {
            simpleEmailService.send(new Mail(adminConfig.getAdminMail(), null, SUBJECT,
                    "Currently in database you have got: " + size + " task"));
        } else {
            simpleEmailService.send(new Mail(adminConfig.getAdminMail(), null, SUBJECT,
                    "Currently in database you have got: " + size + " tasks"));
        }
    }
}
