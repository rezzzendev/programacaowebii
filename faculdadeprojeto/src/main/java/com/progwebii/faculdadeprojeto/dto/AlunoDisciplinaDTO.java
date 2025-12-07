package com.progwebii.faculdadeprojeto.dto;

import lombok.Data;
import java.util.List;

@Data
public class AlunoDisciplinaDTO {

    private String nome;
    private Double nota1;
    private Double nota2;
    private Double media;
    private Double notaNecessaria;
    private Integer faltas;
    private Integer faltasRestantes;
    private Integer presencas;
    private List<String> horariosProvas;
}
