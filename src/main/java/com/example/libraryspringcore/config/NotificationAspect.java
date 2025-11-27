package com.example.libraryspringcore.config;

import com.example.libraryspringcore.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class NotificationAspect {
    private final NotificationService notificationService;

    @Around("@annotation(annotation)")
    public Object sendNotification(ProceedingJoinPoint pjp, NotifyInLog annotation) throws Throwable {

        Object result = pjp.proceed();

        String value = annotation.value();
        if (value != null && !value.isBlank()) {
            Object[] args = pjp.getArgs();
            // MessageFormat использует индексы {0}, {1}
            String message = java.text.MessageFormat.format(value, args);
            notificationService.notify(message);
        }
        return result;
    }
}
