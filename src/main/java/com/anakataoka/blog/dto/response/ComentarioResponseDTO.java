package com.anakataoka.blog.dto.response;

import com.anakataoka.blog.entity.Publicacao;
import com.anakataoka.blog.entity.Usuario;

import java.time.LocalDateTime;

public record ComentarioResponseDTO(
        Long id,
        String texto,
        LocalDateTime dataCriacao,
        Long usuarioId,
        Long publicacaoId
) {
}
