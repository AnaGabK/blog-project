package com.anakataoka.blog.dto.response;

import com.anakataoka.blog.entity.Comentario;
import com.anakataoka.blog.entity.Perfil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String imagem,
        LocalDateTime dataCriacao,
        Set<Long> perfilIds,
        List<Comentario> comentarios
) {}
