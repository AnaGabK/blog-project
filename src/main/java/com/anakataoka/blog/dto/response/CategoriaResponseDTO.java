package com.anakataoka.blog.dto.response;

import com.anakataoka.blog.entity.Publicacao;

import java.util.Set;

public record CategoriaResponseDTO(
        Long id,
        String tag
) {
}
