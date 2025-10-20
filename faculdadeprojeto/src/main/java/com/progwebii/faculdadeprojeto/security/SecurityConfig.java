package com.progwebii.faculdadeprojeto.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager; // MUDANÇA
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration; // MUDANÇA
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer; // MUDANÇA
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration; // MUDANÇA
import org.springframework.web.cors.CorsConfigurationSource; // MUDANÇA
import org.springframework.web.cors.UrlBasedCorsConfigurationSource; // MUDANÇA

import java.util.List; // MUDANÇA

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
                                                            BCryptPasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    // MUDANÇA 1: Expor o AuthenticationManager como um Bean
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // MUDANÇA 2: Definir a configuração de CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Permite requisições do seu app Angular
        configuration.setAllowedOrigins(List.of("http://localhost:4200")); 
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "X-XSRF-TOKEN"));
        // ESSENCIAL para o Angular 'withCredentials: true' funcionar
        configuration.setAllowCredentials(true); 
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Aplica para todas as rotas
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // MUDANÇA 3: Aplicar a configuração de CORS
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) 
                // MUDANÇA 4: Desativar CSRF para APIs (ou configurar um 'CookieCsrfTokenRepository')
                .csrf(AbstractHttpConfigurer::disable) 
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                // MUDANÇA 5: Permitir os endpoints da API de autenticação
                                "/api/auth/login",
                                "/api/auth/register",
                                "/css/**" // Manter se tiver CSS no backend
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                // MUDANÇA 6: Remover o .formLogin() e .logout()
                // A autenticação agora é feita pelo AuthController
                // O logout pode ser um endpoint POST /api/auth/logout simples no futuro
                .logout(logout -> logout.permitAll()); 

        return http.build();
    }
}
