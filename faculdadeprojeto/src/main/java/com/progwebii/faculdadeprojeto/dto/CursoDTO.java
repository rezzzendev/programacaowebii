package com.progwebii.faculdadeprojeto.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CursoDTO {

    private String cursoNome;
    private Integer cursoDuracao;
}
