package com.anakataoka.blog.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usuario")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_usuario", nullable = false)
    private Long id;

    @Column(nullable = false, length = 20)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name="foto_perfil")
    private String imagem;

    @Column(nullable = false)
    private String senha;

    @Column(name = "created_at", insertable = false, updatable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dataCriacao;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_perfil",
            joinColumns = @JoinColumn(name = "cod_usuario"),
            inverseJoinColumns = @JoinColumn(name = "cod_perfil")
    )
    private Set<Perfil> perfis = new HashSet<>();

    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios = new ArrayList<>();
}
