package com.anakataoka.blog.dto.response;

import java.time.LocalDateTime;

public record ComentarioResponseDTO(
        Long id,
        String texto,
        LocalDateTime dataCriacao,
        Long usuarioId,
        Long publicacaoId
) {
}
