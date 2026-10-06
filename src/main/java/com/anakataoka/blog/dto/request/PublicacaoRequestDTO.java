package com.anakataoka.blog.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record PublicacaoRequestDTO(
        @NotBlank String titulo,

        @NotBlank String descricao,

        @NotBlank String imagem,

        @NotNull Long usuarioId,

        Set<Long> categoriasId
) {}
