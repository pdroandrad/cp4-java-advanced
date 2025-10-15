package com.example.MiniMercado.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //@Autowired
    //private UserDetailServiceImplementation userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/css/**", "/js/**", "/static/**", "/*.css", "/signin.html").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/estoquista/**").hasAnyRole("ESTOQUISTA")
                .anyRequest().authenticated()
        )
        .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
                .successHandler((request, response, authentication) -> {
                    boolean isAdmin = authentication.getAuthorities().stream()
                            .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
                    boolean isOperator = authentication.getAuthorities().stream()
                            .anyMatch(a -> a.getAuthority().equals("ROLE_ESTOQUISTA"));

                    if (isAdmin) {
                        response.sendRedirect("/admin/index.html");
                    } else if (isOperator) {
                        response.sendRedirect("/estoquista/index.html");
                    } else {
                        response.sendRedirect("/login?unauthorized");
                    }
                })
        )
        .logout(logout -> logout
                .permitAll()
                .logoutSuccessUrl("/")
        )
        .exceptionHandling(exception -> exception
                .accessDeniedPage("/error/403.html")
        );
return http.build();
}


}
