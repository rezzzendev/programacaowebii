package com.progwebii.faculdadeprojeto.controller;

import com.progwebii.faculdadeprojeto.dto.UsuarioDTO; // Importe o DTO
import com.progwebii.faculdadeprojeto.model.Usuario;
import com.progwebii.faculdadeprojeto.service.UserDetailsServiceImpl;
import org.springframework.http.ResponseEntity; // MUDANÇA
import org.springframework.http.HttpStatus; // MUDANÇA
import org.springframework.security.authentication.AuthenticationManager; // MUDANÇA
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; // MUDANÇA
import org.springframework.security.core.Authentication; // MUDANÇA
import org.springframework.security.core.context.SecurityContextHolder; // MUDANÇA
import org.springframework.web.bind.annotation.*; // MUDANÇA

import java.lang.reflect.Field;
import java.util.Map; // MUDANÇA

@RestController // MUDANÇA 1: De @Controller para @RestController
@RequestMapping("/api/auth") // MUDANÇA 2: Adiciona o prefixo da API (igual ao Angular)
public class AuthController {

    private final UserDetailsServiceImpl userService;
    private final AuthenticationManager authenticationManager; // MUDANÇA 3: Necessário para o login

    // MUDANÇA 4: Injetar o AuthenticationManager
    public AuthController(UserDetailsServiceImpl userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    // MUDANÇA 5: Endpoint de Login que o Angular espera
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
            // O frontend só precisa saber que deu certo (status 200 OK)
            // O cookie de sessão (JSESSIONID) será enviado automaticamente por causa do withCredentials
            return ResponseEntity.ok().body(Map.of("message", "Login bem-sucedido!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body("Erro de autenticação: " + e.getMessage());
        }
    }

    // MUDANÇA 6: Endpoint de Registro que o Angular espera
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UsuarioDTO usuarioDTO) { // MUDANÇA 7: Usar @RequestBody e o DTO
        try {
            // Validação simples (pode reusar seu método 'validarCamposObrigatorios')
            if (usuarioDTO.getLogin() == null || usuarioDTO.getLogin().isBlank() ||
                usuarioDTO.getSenha() == null || usuarioDTO.getSenha().isBlank() ||
                usuarioDTO.getUsuarioNome() == null || usuarioDTO.getUsuarioNome().isBlank() ||
                usuarioDTO.getEmail() == null || usuarioDTO.getEmail().isBlank()) {
                
                return ResponseEntity.badRequest().body("Preencha todos os campos obrigatórios!");
            }

            userService.salvarUsuario(
                    usuarioDTO.getLogin(),
                    usuarioDTO.getSenha(),
                    usuarioDTO.getUsuarioNome(),
                    usuarioDTO.getEmail()
            );

            return ResponseEntity.ok(Map.of("message", "Usuário cadastrado com sucesso!")); // MUDANÇA 8: Retornar JSON

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Erro ao registrar usuário: " + e.getMessage());
        }
    }

    // Os métodos GET para /login, /register e /home foram removidos
    // porque eles serviam templates HTML, o que agora é responsabilidade do Angular.
}
