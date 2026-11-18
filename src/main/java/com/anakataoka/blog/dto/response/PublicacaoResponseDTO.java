package com.anakataoka.blog.dto.response;

import java.time.LocalDateTime;
import java.util.Set;

public record PublicacaoResponseDTO(
        Long id,
        String titulo,
        String descricao,
        String imagem,
        LocalDateTime dataCriacao,
        Long usuarioId,
        Set<Long> categoriaIds
) {
}
