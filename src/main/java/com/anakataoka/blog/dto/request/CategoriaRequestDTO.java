package com.anakataoka.blog.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record CategoriaRequestDTO(@NotBlank String tag,
                                  Set<Long> publicacoesId ) {
}
