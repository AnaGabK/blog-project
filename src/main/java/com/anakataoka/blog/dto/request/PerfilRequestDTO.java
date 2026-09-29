package com.anakataoka.blog.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record PerfilRequestDTO (
        @NotBlank String nome,

        List<Long> usuariosIds
){
}
