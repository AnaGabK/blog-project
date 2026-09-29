package com.anakataoka.blog.dto.response;

import com.anakataoka.blog.entity.Usuario;

import java.util.Set;

public record PerfilResponseDTO(
        Long id,
        String nome
) {
}
