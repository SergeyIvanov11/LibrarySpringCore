package com.example.libraryspringcore.config;

import com.example.libraryspringcore.dto.Loan;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class LoanBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String name) {
        if (bean instanceof Loan) {
            System.out.println("Заем создан через BPP (до init): " + bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (bean instanceof Loan) {
            System.out.println("Заем создан через BPP (после init): " + bean);
        }
        return bean;
    }
}
