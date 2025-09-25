package com.progwebii.faculdadeprojeto.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AlunoDTO {

    private String nome;
    private LocalDate dataNascimento;
    private String matricula;
}
