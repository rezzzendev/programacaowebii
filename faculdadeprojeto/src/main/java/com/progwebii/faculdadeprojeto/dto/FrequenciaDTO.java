package com.progwebii.faculdadeprojeto.dto;

import lombok.Data;

@Data
public class FrequenciaDTO {
    private String matricula;
    private Long disciplinaId;
    private Integer presencas = 0;
    private Integer faltas = 0;
}
