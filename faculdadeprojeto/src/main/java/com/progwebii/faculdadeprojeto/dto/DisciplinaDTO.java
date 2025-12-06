package com.progwebii.faculdadeprojeto.dto;

import lombok.Data;

@Data
public class DisciplinaDTO {
    private Long id;
    private String nome;
    private Long cursoId;
    private Long professorId;
}
