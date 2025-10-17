package com.example.MiniMercado.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/css/**", "/js/**", "/static/**", "/*.css", "/signup").permitAll()
                        .requestMatchers("/produtos/*/edit", "api/produtos/**", "swagger/").hasRole("ADMIN")
                        .requestMatchers("/produtos/cadastro" ).hasAnyRole("ADMIN", "ESTOQUISTA")
                        .requestMatchers("/index.html", "/").hasAnyRole("ADMIN", "ESTOQUISTA")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .successHandler((request, response, authentication) -> {
                            boolean isAdmin = authentication.getAuthorities().stream()
                                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
                            boolean isEstoquista = authentication.getAuthorities().stream()
                                    .anyMatch(a -> a.getAuthority().equals("ROLE_ESTOQUISTA"));

                            if (isAdmin || isEstoquista) {
                                response.sendRedirect("/");
                            } else {
                                response.sendRedirect("/login?error=true");
                            }
                        })
                        .failureUrl("/login?error=true")
                )

                .logout(logout -> logout
                        .permitAll()
                        .logoutSuccessUrl("/login")
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/403")
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
