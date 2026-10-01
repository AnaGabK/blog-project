package com.anakataoka.blog.mapper;

import com.anakataoka.blog.dto.request.UsuarioRequestDTO;
import com.anakataoka.blog.dto.response.UsuarioResponseDTO;
import com.anakataoka.blog.entity.Perfil;
import com.anakataoka.blog.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper {
    public Usuario toEntity(UsuarioRequestDTO dto){
        if(dto == null){
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setImagem(dto.imagem());
        usuario.setSenha(dto.senha());

        return usuario;
    }

    public UsuarioResponseDTO toResponseDTO(Usuario usuario){
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getImagem(),
                usuario.getDataCriacao(),
                toPerfilIds(usuario.getPerfis()),
                usuario.getComentarios()
        );
    }

    private Set<Long> toPerfilIds(Set<Perfil> perfis){
        if(perfis == null) {
            return Collections.emptySet();
        }

        return perfis.stream()
                .map(Perfil::getId)
                .collect(Collectors.toSet());
    }
}
