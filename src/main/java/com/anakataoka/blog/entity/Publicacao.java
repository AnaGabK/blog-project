package com.anakataoka.blog.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "publicacao")
@Data
public class Publicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_publicacao")
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(length = 250)
    private String descricao;

    @Column(name = "imagem")
    private String imagem;

    @OneToMany(mappedBy = "publicacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "publicacao_categoria",
            joinColumns = @JoinColumn(name = "cod_publicacao"),
            inverseJoinColumns = @JoinColumn(name = "cod_categoria")
    )
    private Set<Categoria> categorias = new HashSet<>();
}
