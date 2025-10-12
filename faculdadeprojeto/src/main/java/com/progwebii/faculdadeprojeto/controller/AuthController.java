package com.progwebii.faculdadeprojeto.controller;

import com.progwebii.faculdadeprojeto.model.Usuario;
import com.progwebii.faculdadeprojeto.service.UserDetailsServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Field;

@Controller
public class AuthController {

    private final UserDetailsServiceImpl userService;

    public AuthController(UserDetailsServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String login,
            @RequestParam String senha,
            @RequestParam String usuarioNome,
            @RequestParam String email
    ) {
        try {

            Usuario novoUsuario = Usuario.class.getDeclaredConstructor().newInstance();

            Field fLogin = Usuario.class.getDeclaredField("login");
            Field fSenha = Usuario.class.getDeclaredField("senha");
            Field fNome = Usuario.class.getDeclaredField("usuarioNome");
            Field fEmail = Usuario.class.getDeclaredField("email");


            fLogin.setAccessible(true);
            fSenha.setAccessible(true);
            fNome.setAccessible(true);
            fEmail.setAccessible(true);


            fLogin.set(novoUsuario, login);
            fSenha.set(novoUsuario, senha);
            fNome.set(novoUsuario, usuarioNome);
            fEmail.set(novoUsuario, email);


            System.out.println("Usuário criado via reflexão:");
            for (Field f : Usuario.class.getDeclaredFields()) {
                f.setAccessible(true);
                System.out.println(f.getName() + " = " + f.get(novoUsuario));
            }


            userService.salvarUsuario(
                    novoUsuario.getLogin(),
                    novoUsuario.getSenha(),
                    novoUsuario.getUsuarioNome(),
                    novoUsuario.getEmail()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
