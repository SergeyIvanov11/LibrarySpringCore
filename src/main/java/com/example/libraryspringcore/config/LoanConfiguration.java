package com.example.libraryspringcore.config;

import com.example.libraryspringcore.dto.Loan;
import org.springframework.context.annotation.*;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.example.libraryspringcore")
public class LoanConfiguration {

    @Bean(initMethod = "init")
    @Scope("prototype")
    public Loan loan() {
        return new Loan();
    }
}
