package com.anakataoka.blog.dto.request;

import com.anakataoka.blog.entity.Comentario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UsuarioRequestDTO(
        @NotBlank String nome,

        @NotBlank
        @Email
        String email,

        String imagem,

        String senha,

        List<Comentario> comentarios,

        List<Long> perfilIds
) {}
