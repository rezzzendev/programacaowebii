package com.progwebii.faculdadeprojeto.dto;

import lombok.Data;


@Data
public class UsuarioDTO {

    private Long id;
    private String login;
    private String senha;

    // ✅ ESTE CAMPO ESTAVA FALTANDO
    private String usuarioNome;

    private String email;
}

