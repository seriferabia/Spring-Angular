package com.company.myredditbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import org.thymeleaf.context.Context;


@Service
@RequiredArgsConstructor
public class MailContentBuilder {
    private final TemplateEngine templateEngine;

    public String build(String message) {
        Context context = new Context();
        context.setVariable("message", message);

        // Converts string into html style using template and context variable(which is the message)
        return templateEngine.process("mailTemplate", context);
    }
}
