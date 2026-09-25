package com.anakataoka.blog.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "comentario")
@Data
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_comentario")
    private Long id;

    @Column(name = "texto", length = 100, nullable = false)
    private String texto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_publicacao", nullable = false)
    private Publicacao publicacao;
}
