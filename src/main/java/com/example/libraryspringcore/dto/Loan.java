package com.example.libraryspringcore.dto;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;

import java.time.LocalDate;

@Data
@Getter
@Setter
@Scope("prototype")
public class Loan {
    private Book book;
    private Reader reader;
    private LocalDate loanDate;

    @PostConstruct
    public void onCreate() {
        System.out.println("Заем создан через @PostConstruct");
    }

    public void init() {
        System.out.println("Заем создан через init-метод");
    }
}
