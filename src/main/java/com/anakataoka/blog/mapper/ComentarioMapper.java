package com.anakataoka.blog.mapper;

import com.anakataoka.blog.dto.request.ComentarioRequestDTO;
import com.anakataoka.blog.dto.response.ComentarioResponseDTO;
import com.anakataoka.blog.entity.Comentario;
import com.anakataoka.blog.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class ComentarioMapper {
    public Comentario toEntity(ComentarioRequestDTO dto){
        if(dto == null){
            return null;
        }
        Comentario comentario = new Comentario();
        comentario.setTexto(dto.texto());
        return comentario;
    }

    public ComentarioResponseDTO toResponseDTO(Comentario comentario){
        return new ComentarioResponseDTO(
                comentario.getId(),
                comentario.getTexto(),
                comentario.getDataCriacao(),
                comentario.getUsuario().getId(),
                comentario.getPublicacao().getId()
        );
    }
}
