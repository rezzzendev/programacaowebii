package com.progwebii.faculdadeprojeto.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProvaDTO {
    private Long disciplinaId;
    private LocalDateTime dataHora;
}
