package com.example.libraryspringcore.dto;

import com.example.libraryspringcore.dto.Enum.Type;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Reader {
    private Long id;
    private String name;
    private Type type;
}
