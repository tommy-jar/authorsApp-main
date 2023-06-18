package com.upc.curso.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
@Setter
@Getter
public class AuthorDTO {
    private Long id;
    private String nameAuthor;
    private LocalDate birthDateAuthor;
    private String emailAuthor;

}
