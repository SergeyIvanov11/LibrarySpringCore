package com.example.libraryspringcore.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Book {
    private Long id;
    private String title;
    private String author;
    private boolean available;
}
