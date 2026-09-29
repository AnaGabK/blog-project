package com.anakataoka.blog.dto.request;

import com.anakataoka.blog.entity.Comentario;
import com.anakataoka.blog.entity.Perfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Set;

public record UsuarioRequestDTO(
        @NotBlank String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank String imagem,

        String senha,

        List<Comentario> comentarios,

        List<Long> perfilIds
) {}
