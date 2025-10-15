package com.example.MiniMercado.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="TB_USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id_usuario;

    @NotBlank
    @Column(nullable = false, length = 20, unique=true)
    private String username;

    @NotBlank
    @Column(nullable = false, length = 255)
    private String password;
}
