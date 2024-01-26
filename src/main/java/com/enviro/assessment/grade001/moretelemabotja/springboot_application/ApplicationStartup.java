package com.enviro.assessment.grade001.moretelemabotja.springboot_application;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.UserCommandLine;


@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    private final UserCommandLine userCommandLine;

    public ApplicationStartup(UserCommandLine userCommandLine) {
        this.userCommandLine = userCommandLine;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("Application is ready. Starting CommandLineApp...");
        userCommandLine.run();
    }
}
