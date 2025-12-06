package com.progwebii.faculdadeprojeto.controller;

import com.progwebii.faculdadeprojeto.dto.UsuarioDTO;
import com.progwebii.faculdadeprojeto.model.Usuario;
import com.progwebii.faculdadeprojeto.service.UserDetailsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Field;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class  AuthController {

    private final UserDetailsServiceImpl userService;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserDetailsServiceImpl userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody Map<String, String> loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.get("username"),
                            loginRequest.get("password")
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok().body(Map.of("message", "Login bem-sucedido!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body("Erro de autenticação, usuário ou senha inválido");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UsuarioDTO usuarioDTO) {
        try {

            String erro = validarCamposObrigatorios(usuarioDTO);
            if (erro != null) {
                return ResponseEntity.badRequest().body(Map.of("error", erro));
            }

            userService.salvarUsuario(
                    usuarioDTO.getLogin(),
                    usuarioDTO.getSenha(),
                    usuarioDTO.getUsuarioNome(),
                    usuarioDTO.getEmail()
            );


            return ResponseEntity.ok(Map.of("message", "Usuário cadastrado com sucesso!"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Erro ao registrar usuário"));
        }
    }

    private String validarCamposObrigatorios(Object obj) throws IllegalAccessException {
        for (Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);

            if (f.getName().equals("id") || f.getName().equals("status"))
                continue;

            Object valor = f.get(obj);
            if (valor == null || valor.toString().isBlank()) {
                return "O campo '" + f.getName() + "' está vazio!";
            }
        }
        return null;
    }


}
