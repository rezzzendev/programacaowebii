package com.progwebii.faculdadeprojeto.dto;

import lombok.Data;

@Data
public class NotaDTO {
    private Long id;
    private String matriculaAluno; // usa matricula string
    private Long disciplinaId;
    private Double bimestre1;
    private Double bimestre2;
}
