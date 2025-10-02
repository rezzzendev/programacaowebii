package com.progwebii.faculdadeprojeto.dto;

import lombok.Data;

@Data
public class UsuarioDTO {

    private String login;
    private String senha;
    private String email;
    private String usuarioNome;
}
