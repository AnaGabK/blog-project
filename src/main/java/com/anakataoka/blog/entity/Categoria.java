package com.anakataoka.blog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categoria")
@Getter
@Setter
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_categoria", nullable = false)
    private Long id;

    @Column(nullable = false, length = 20)
    private String tag;

    @ManyToMany(mappedBy = "categorias")
    private Set<Publicacao> publicacoes = new HashSet<>();
}
