package com.progwebii.faculdadeprojeto.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class AlunoDTO {

    private String matricula;
    private String nome;
    private String email;
    private LocalDate dataNascimento;

    private Long cursoId;

    private List<Long> disciplinasIds;
}
