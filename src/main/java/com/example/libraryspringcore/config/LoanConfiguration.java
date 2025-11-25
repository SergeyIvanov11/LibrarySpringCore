package com.example.libraryspringcore.config;

import com.example.libraryspringcore.dto.Loan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("com.example.libraryspringcore")
public class LoanConfiguration {

    @Bean(initMethod = "init")
    @Scope("prototype")
    public Loan loan() {
        return new Loan();
    }
}
