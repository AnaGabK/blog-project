package com.anakataoka.blog.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record PublicacaoRequestDTO(
        @NotBlank String titulo,

        @NotBlank String descricao,

        @NotBlank String imagem,

        @NotBlank Long usuarioId,

        Set<Long> categoriasId
) {}
