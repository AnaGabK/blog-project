package com.anakataoka.blog.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ComentarioRequestDTO(@NotBlank String texto,
                                   @NotNull Long usuarioId,
                                   @NotNull Long publicacaoId) {
}
