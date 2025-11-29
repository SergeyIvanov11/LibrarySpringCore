package com.example.libraryspringcore.config;

import com.example.libraryspringcore.dto.Loan;
import org.springframework.context.annotation.*;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = false) // false => JDK dynamic proxies
public class LoanConfiguration {

    @Bean(initMethod = "init")
    @Scope("prototype")
    public Loan loan() {
        return new Loan();
    }
}
