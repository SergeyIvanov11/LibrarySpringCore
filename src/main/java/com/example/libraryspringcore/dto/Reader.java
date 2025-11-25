package com.example.libraryspringcore.dto;

import com.example.libraryspringcore.dto.Enum.Type;
import lombok.Data;

@Data
public class Reader {
    private Long id;
    private String name;
    private Type type;
}
