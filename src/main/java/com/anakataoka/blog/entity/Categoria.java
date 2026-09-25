package com.anakataoka.blog.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "categoria")
@Data
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
