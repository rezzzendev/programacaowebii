package com.progwebii.faculdadeprojeto.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AlunoDTO {
    private String matricula;   // agora string
    private String nome;
    private String email;
    private Long cursoId;
    private LocalDate dataNascimento;
}
