package com.anakataoka.blog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "perfil")
@Getter
@Setter
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_perfil", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false, unique = true, length = 20)
    private String nome;

    @ManyToMany(mappedBy = "perfis")
    private Set<Usuario> usuarios = new HashSet<>();
}
